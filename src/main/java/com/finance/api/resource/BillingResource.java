package com.finance.api.resource;

import com.finance.api.event.ResourceCreatedEvent;
import com.finance.api.model.Billing;
import com.finance.api.repository.BillingRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/billings")
public class BillingResource {

    private final BillingRepository billingRepository;

    private final ApplicationEventPublisher publisher;

    public BillingResource(BillingRepository billingRepository, ApplicationEventPublisher publisher) {
        this.billingRepository = billingRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Billing> find() {
        return this.billingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Billing findById(@PathVariable Long id) {
        return this.billingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Billing> create(@Valid @RequestBody Billing billing, HttpServletResponse response) {
        Billing billingSaved = this.billingRepository.save(billing);
        this.publisher.publishEvent(new ResourceCreatedEvent(this, response, billingSaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(billingSaved);
    }

}
