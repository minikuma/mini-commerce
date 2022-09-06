package me.minikuma.domain.customer;

import lombok.*;
import me.minikuma.domain.TimeEntity;
import me.minikuma.domain.address.Address;
import me.minikuma.domain.payment.Payment;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "CUSTOMER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends TimeEntity implements Serializable {
    @Id
    @Column(name = "CUSTOMER_ID", updatable = false, insertable = false, unique = true)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Builder
    public Customer(String userName, String passWord, String phoneNumber) {
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
    }

    // 연관 관계 메서드 (고객, 지불 수단)
    public void changePayment(Payment payment) {
        this.payment = payment;
        payment.getCustomers().add(this);
    }

    // 연관 관계 메서드 (고객, 주소)
    public void changeAddress(Address address) {
        this.address = address;
        address.getCustomers().add(this);
    }
}