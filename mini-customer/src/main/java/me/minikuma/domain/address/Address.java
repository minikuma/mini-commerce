package me.minikuma.domain.address;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minikuma.domain.TimeEntity;
import me.minikuma.domain.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ADDRESS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends TimeEntity implements Serializable {
    @Id
    @Column(name = "ADDRESS_ID", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @OneToMany(mappedBy = "address")
    private List<Customer> customers = new ArrayList<>();

    @Builder
    public Address(String zipCode, String phoneNumber, String mobileNumber) {
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }
}
