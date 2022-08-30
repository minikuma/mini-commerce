package me.minikuma.domain.payment;

import lombok.*;
import me.minikuma.domain.TimeEntity;
import me.minikuma.domain.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends TimeEntity implements Serializable {
    @Id
    @Column(name = "PAYMENT_ID", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "EXPIRY")
    private String expiry;

    @OneToMany(mappedBy = "payment")
    private List<Customer> customers = new ArrayList<>();

    @Builder
    public Payment(String paymentType, String provider, String accountNumber, String expiry) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiry = expiry;
    }
}
