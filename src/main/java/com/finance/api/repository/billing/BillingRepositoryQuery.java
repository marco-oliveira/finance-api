package com.finance.api.repository.billing;

import com.finance.api.model.Billing;
import com.finance.api.repository.filter.BillingFilter;

import java.util.List;

public interface BillingRepositoryQuery {

    List<Billing> filter(BillingFilter billingFilter);
}
