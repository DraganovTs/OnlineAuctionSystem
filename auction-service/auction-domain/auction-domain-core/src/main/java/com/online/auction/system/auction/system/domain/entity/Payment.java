package com.online.auction.system.auction.system.domain.entity;

import com.online.auction.system.common.domain.entity.AggregateRoot;
import com.online.auction.system.common.domain.valueobject.PaymentId;

public class Payment extends AggregateRoot<PaymentId> {

    private boolean completed;

    public Payment(Builder builder) {
        super.setId(builder.paymentId);
        completed = builder.completed;
    }

    public boolean isCompleted() {
        return completed;
    }


    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private PaymentId paymentId;
        private boolean completed;

        private Builder() {
        }

        public Builder paymentId(PaymentId val) {
            paymentId = val;
            return this;
        }

        public Builder completed(boolean val) {
            completed = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
