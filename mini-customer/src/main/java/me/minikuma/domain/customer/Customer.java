package me.minikuma.domain.customer;

import lombok.*;
import me.minikuma.domain.TimeEntity;
import me.minikuma.domain.payment.Payment;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "CUSTOMER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends TimeEntity implements Serializable {
    @Id
    @Column(name = "CUSTOMER_ID", updatable = true, insertable = true, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String passWord;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @Builder
    public Customer(String userName, String passWord, String phoneNumber) {
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
    }
    public void changePayment(Payment payment) {
        this.payment = payment;
        payment.getCustomers().add(this);
    }
}