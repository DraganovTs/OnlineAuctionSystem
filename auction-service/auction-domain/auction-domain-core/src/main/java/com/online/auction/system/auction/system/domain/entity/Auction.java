package com.online.auction.system.auction.system.domain.entity;

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
    private final LocalDateTime startTime;

    private AuctionStatus status;
    private Money highestBid;
    private List<String> FailureMessages;
    private LocalDateTime endTime;

    private Auction(Builder builder) {
        super.setId(builder.auctionId);
        paymentId = builder.paymentId;
        userId = builder.userId;
        title = builder.title;
        description = builder.description;
        startTime = builder.startTime;
        status = builder.status;
        highestBid = builder.highestBid;
        FailureMessages = builder.FailureMessages;
        endTime = builder.endTime;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public Money getHighestBid() {
        return highestBid;
    }

    public List<String> getFailureMessages() {
        return FailureMessages;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public static final class Builder {
        private AuctionId auctionId;
        private PaymentId paymentId;
        private UserId userId;
        private String title;
        private String description;
        private LocalDateTime startTime;
        private AuctionStatus status;
        private Money highestBid;
        private List<String> FailureMessages;
        private LocalDateTime endTime;

        private Builder() {
        }

        public static Builder Builder() {
            return new Builder();
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

        public Builder startTime(LocalDateTime val) {
            startTime = val;
            return this;
        }

        public Builder status(AuctionStatus val) {
            status = val;
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
