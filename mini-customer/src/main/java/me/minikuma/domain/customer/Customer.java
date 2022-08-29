package me.minikuma.domain.customer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minikuma.domain.TimeEntity;
import me.minikuma.domain.address.Address;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends TimeEntity {
    @Id
    @Column(name = "customerId", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Address address;

    @Builder
    public Customer(Long customerId, String userName, String passWord, String phoneNumber, Address address) {
        this.customerId = customerId;
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}