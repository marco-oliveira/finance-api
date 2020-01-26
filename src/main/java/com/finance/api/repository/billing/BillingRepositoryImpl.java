package com.finance.api.repository.billing;

import com.finance.api.model.Billing;
import com.finance.api.repository.filter.BillingFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillingRepositoryImpl implements BillingRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Billing> filter(BillingFilter billingFilter, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Billing> criteria = builder.createQuery(Billing.class);
        Root<Billing> root = criteria.from(Billing.class);

        //create constraints
        Predicate[] predicates = createConstraints(billingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Billing> query = this.entityManager.createQuery(criteria);
        createConstraintsForPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, getTotalPage(billingFilter));
    }

    private Predicate[] createConstraints(BillingFilter billingFilter, CriteriaBuilder builder, Root<Billing> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(billingFilter.getDescription())) {
            predicates.add(builder.like(
                builder.lower(root.get("description")), getLikeWithParameter(billingFilter.getDescription())
            ));
        }

        if (Objects.nonNull(billingFilter.getDueDate())) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dueDate"), billingFilter.getDueDate()));
        }

        if (Objects.nonNull(billingFilter.getPaymentDate())) {
            predicates.add(builder.lessThanOrEqualTo(root.get("paymentDate"), billingFilter.getPaymentDate()));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private String getLikeWithParameter(String param) {
        return "%" + param + "%";
    }

    private void createConstraintsForPagination(TypedQuery<Billing> query, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int firstRegisterPage = pageNumber * pageSize;
        query.setFirstResult(firstRegisterPage);
        query.setMaxResults(pageSize);
    }

    private Long getTotalPage(BillingFilter billingFilter) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Billing> root = criteria.from(Billing.class);

        Predicate[] predicates = createConstraints(billingFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }
}
