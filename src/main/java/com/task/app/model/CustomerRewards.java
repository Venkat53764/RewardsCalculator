package com.task.app.model;

public class CustomerRewards {

    private Long customerId;
    private Long currentMonthRewardPoints;
    private Long previousMonthRewardPoints;
    private Long thirdMonthRewardPoints;
    private Long totalRewards;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCurrentMonthRewardPoints() {
        return currentMonthRewardPoints;
    }

    public void setCurrentMonthRewardPoints(Long currentMonthRewardPoints) {
        this.currentMonthRewardPoints = currentMonthRewardPoints;
    }

    public Long getPreviousMonthRewardPoints() {
        return previousMonthRewardPoints;
    }

    public void setPreviousMonthRewardPoints(Long previousMonthRewardPoints) {
        this.previousMonthRewardPoints = previousMonthRewardPoints;
    }

    public Long getThirdMonthRewardPoints() {
        return thirdMonthRewardPoints;
    }

    public void setThirdMonthRewardPoints(Long thirdMonthRewardPoints) {
        this.thirdMonthRewardPoints = thirdMonthRewardPoints;
    }

    public Long getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(Long totalRewards) {
        this.totalRewards = totalRewards;
    }

    @Override
    public String toString() {
        return "CustomerRewards {" +
                "customerId=" + customerId +
                ", currentMonthRewardPoints=" + currentMonthRewardPoints +
                ", previousMonthRewardPoints=" + previousMonthRewardPoints +
                ", thirdMonthRewardPoints=" + thirdMonthRewardPoints +
                ", totalRewards=" + totalRewards +
                '}';
    }
}
