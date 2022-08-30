package me.minikuma.domain.address;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minikuma.domain.TimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "ADDRESS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends TimeEntity implements Serializable {
    @Id
    @Column(name = "ADDRESS_ID", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Builder
    public Address(Long customerId, String zipCode, String phoneNumber, String mobileNumber) {
        this.customerId = customerId;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }
}
