package com.task.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.app.domain.CustomerTransaction;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CustomerTransactionRepository extends CrudRepository<CustomerTransaction, Long> {

    List<CustomerTransaction> findByCustomerIdAndTransactionDateBetween(Long customerId, Date fromDate, Date toDate);

    List<CustomerTransaction> findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
}