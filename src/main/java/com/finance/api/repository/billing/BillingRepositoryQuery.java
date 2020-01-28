package com.finance.api.repository.billing;

import com.finance.api.model.Billing;
import com.finance.api.repository.filter.BillingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillingRepositoryQuery {

    Page<Billing> filter(BillingFilter billingFilter, Pageable pageable);
}
