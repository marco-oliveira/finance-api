package com.finance.api.repository;

import com.finance.api.model.Billing;
import com.finance.api.repository.billing.BillingRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long>, BillingRepositoryQuery {
}
