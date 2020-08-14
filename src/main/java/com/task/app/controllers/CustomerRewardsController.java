package com.task.app.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.app.domain.CustomerTransaction;
import com.task.app.model.CustomerRewards;
import com.task.app.repositories.CustomerTransactionRepository;
import com.task.app.services.CustomerRewardsService;
import com.task.app.util.Constants;

@RestController
@RequestMapping("/customer")
public class CustomerRewardsController {

    @Autowired
    private CustomerRewardsService customerRewardsService;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @GetMapping(value = "/{customerId}/rewards")
    public CustomerRewards getCustomerRewards(@PathVariable("customerId") Long customerId) {

        Date currentDate = new Date();
        Date thirtyDaysOffsetDate = getDatePriorToDays(Constants.THIRTY_DAY_OFFSET);
        Date sixtyDaysOffsetDate = getDatePriorToDays(Constants.SIXTY_DAY_OFFSET);
        Date ninetyDaysOffsetDate = getDatePriorToDays(Constants.NINTEY_DAY_OFFSET);

        List<CustomerTransaction> thirtyDayTranscactions = customerTransactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, thirtyDaysOffsetDate, currentDate);
        List<CustomerTransaction> sixtyDayTransactions = customerTransactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, sixtyDaysOffsetDate, thirtyDaysOffsetDate);
        List<CustomerTransaction> ninetyDayTransactions = customerTransactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, ninetyDaysOffsetDate, sixtyDaysOffsetDate);

        return calculateCustomerRewards(customerId, thirtyDayTranscactions, sixtyDayTransactions, ninetyDayTransactions);
    }

    @GetMapping(value = "/{customerId}/transactions")
    public List<CustomerTransaction> getAllTransactionsByCustomerId(@PathVariable("customerId") Long customerId) {
        return customerTransactionRepository.findByCustomerId(customerId);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomerTransactionsById(@PathVariable("customerId") Long customerId) {
        customerTransactionRepository.deleteByCustomerId(customerId);
    }

    private Date getDatePriorToDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    private CustomerRewards calculateCustomerRewards(Long customerId, List<CustomerTransaction> thirtyDayTransactions, List<CustomerTransaction> sixtyDayTransactions, List<CustomerTransaction> ninetyDayTransactions) {
        CustomerRewards customerRewards = new CustomerRewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setCurrentMonthRewardPoints(customerRewardsService.calculate(thirtyDayTransactions));
        customerRewards.setPreviousMonthRewardPoints(customerRewardsService.calculate(sixtyDayTransactions));
        customerRewards.setThirdMonthRewardPoints(customerRewardsService.calculate(ninetyDayTransactions));
        customerRewards.setTotalRewards(customerRewards.getCurrentMonthRewardPoints()
                + customerRewards.getPreviousMonthRewardPoints() + customerRewards.getThirdMonthRewardPoints());
        return customerRewards;
    }
}