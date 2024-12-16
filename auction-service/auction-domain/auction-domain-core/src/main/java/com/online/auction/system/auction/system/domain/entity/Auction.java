package com.online.auction.system.auction.system.domain.entity;

import com.online.auction.system.auction.system.domain.exception.AuctionDomainException;
import com.online.auction.system.common.domain.entity.AggregateRoot;
import com.online.auction.system.common.domain.valueobject.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Auction extends AggregateRoot<AuctionId> {

    private final PaymentId paymentId;
    private final UserId userId;
    private final String title;
    private final String description;
    private final Money startPrice;

    private LocalDateTime startTime;
    private AuctionStatus auctionStatus;
    private Money highestBid;
    private List<String> failureMessages;
    private LocalDateTime endTime;

    private Auction(Builder builder) {
        super.setId(builder.auctionId);
        paymentId = builder.paymentId;
        userId = builder.userId;
        title = builder.title;
        startTime =builder.startTime;
        description = builder.description;
        startPrice = builder.startPrice;
        auctionStatus = builder.auctionStatus;
        highestBid = builder.highestBid;
        failureMessages = builder.FailureMessages;
        endTime = builder.endTime;
    }


    public void initializeAuction() {
        setId(new AuctionId(UUID.randomUUID()));
        auctionStatus = AuctionStatus.ACTIVE;
        startTime = LocalDateTime.now();
        highestBid = startPrice;
    }

    public void validateAuction() {
        validateInitialAuction();
        validateStartingPrice();
    }

    public void placeBid(Money bid) {
        validateBid(bid);
        bid();
        highestBid = bid;
        auctionStatus = AuctionStatus.PENDING;
    }

    public void bid() {
        if (auctionStatus != AuctionStatus.ACTIVE) {
            throw new AuctionDomainException("Auction is not in correct state for bid operation!");
        }
        auctionStatus = AuctionStatus.PENDING;
    }

    public void approve() {
        if (auctionStatus != AuctionStatus.PENDING) {
            throw new AuctionDomainException("Auction is not in correct state for approve operation!");
        }
        endTime = LocalDateTime.now();
        auctionStatus = AuctionStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (auctionStatus != AuctionStatus.PENDING) {
            throw new AuctionDomainException("Auction is not in correct state for initCancel operation!");
        }
        auctionStatus = AuctionStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }


    public void cancel(List<String> failureMessages) {
        if (auctionStatus == AuctionStatus.CANCELLING || auctionStatus == AuctionStatus.ACTIVE) {
            throw new AuctionDomainException("Auction is not in correct state for cancel operation!");
        }
        auctionStatus = AuctionStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    public void close() {
        if (auctionStatus != AuctionStatus.APPROVED && auctionStatus != AuctionStatus.ACTIVE) {
            throw new AuctionDomainException("Auction is not in correct state for close operation!");
        }
        endTime = LocalDateTime.now();
        auctionStatus = AuctionStatus.CLOSED;
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    private void validateInitialAuction() {
        if (auctionStatus != null || getId() != null) {
            throw new AuctionDomainException("Auction is not in correct state for initialization");
        }
    }

    private void validateStartingPrice() {
        if (startPrice == null || !startPrice.isGreaterThanZero()) {
            throw new AuctionDomainException("Auction start price must be greater than zero");
        }
    }

    private void validateBid(Money bid) {
        if (bid == null || !bid.isGreaterThanZero() || !bid.isGreaterThan(startPrice) || !bid.isGreaterThan(highestBid)) {
            throw new AuctionDomainException("Auction bid must be greater than auction starting price");
        }
    }


    public PaymentId getPaymentId() {
        return paymentId;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Money getStartPrice() {
        return startPrice;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public Money getHighestBid() {
        return highestBid;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AuctionId auctionId;
        private PaymentId paymentId;
        private UserId userId;
        private String title;
        private String description;
        private Money startPrice;
        private LocalDateTime startTime;
        private AuctionStatus auctionStatus;
        private Money highestBid;
        private List<String> FailureMessages;
        private LocalDateTime endTime;

        private Builder() {
        }


        public Builder auctionId(AuctionId val) {
            auctionId = val;
            return this;
        }

        public Builder paymentId(PaymentId val) {
            paymentId = val;
            return this;
        }

        public Builder userId(UserId val) {
            userId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder startPrice(Money val) {
            startPrice = val;
            return this;
        }

        public Builder startTime(LocalDateTime val){
            startTime = val;
            return this;
        }

        public Builder auctionStatus(AuctionStatus val) {
            auctionStatus = val;
            return this;
        }

        public Builder highestBid(Money val) {
            highestBid = val;
            return this;
        }

        public Builder FailureMessages(List<String> val) {
            FailureMessages = val;
            return this;
        }

        public Builder endTime(LocalDateTime val) {
            endTime = val;
            return this;
        }

        public Auction build() {
            return new Auction(this);
        }
    }
}
