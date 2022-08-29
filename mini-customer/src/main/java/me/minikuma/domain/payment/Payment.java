package me.minikuma.domain.payment;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minikuma.domain.TimeEntity;

import javax.persistence.*;

@Table(name = "payment")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends TimeEntity {
    @Id
    @Column(name = "paymentId", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "provider")
    private String provider;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "expiry")
    private String expiry;

    @Builder
    public Payment(Long paymentId, Long customerId, String provider, String accountNumber, String expiry) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiry = expiry;
    }
}
