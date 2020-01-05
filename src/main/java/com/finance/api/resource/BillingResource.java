package com.finance.api.resource;

import com.finance.api.model.Billing;
import com.finance.api.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/billings")
public class BillingResource {

    @Autowired
    private BillingRepository billingRepository;

    @GetMapping
    public List<Billing> find() {
        return this.billingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Billing findById(@PathVariable Long id) {
        return this.billingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
