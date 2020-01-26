package com.finance.api.repository.billing;

import com.finance.api.model.Billing;
import com.finance.api.repository.filter.BillingFilter;
import org.apache.commons.lang3.StringUtils;

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
    public List<Billing> filter(BillingFilter billingFilter) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Billing> criteria = builder.createQuery(Billing.class);
        Root<Billing> root = criteria.from(Billing.class);

        //create constraints
        Predicate[] predicates = createConstraints(billingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Billing> query = this.entityManager.createQuery(criteria);
        return query.getResultList();
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
}
