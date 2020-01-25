package com.finance.api.service;

import com.finance.api.exceptionhandler.exception.PersonNotFoundOrInactiveException;
import com.finance.api.model.Billing;
import com.finance.api.model.Person;
import com.finance.api.repository.BillingRepository;
import com.finance.api.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingService {


    private final PersonRepository personRepository;
    private final BillingRepository billingRepository;

    public BillingService(PersonRepository personRepository, BillingRepository billingRepository) {
        this.personRepository = personRepository;
        this.billingRepository = billingRepository;
    }

    public Billing save(Billing billing) {
        Optional<Person> optionalPerson = this.personRepository.findById(billing.getPerson().getId());
        if (!optionalPerson.isPresent() || !optionalPerson.get().getActive()) {
            throw new PersonNotFoundOrInactiveException();
        }
        return this.billingRepository.save(billing);
    }
}
