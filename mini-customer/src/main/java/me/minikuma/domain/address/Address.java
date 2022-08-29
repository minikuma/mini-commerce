package me.minikuma.domain.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minikuma.domain.TimeEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "adderss")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends TimeEntity {
    @Id
    @Column(name = "addressId", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "adderss")
    private String address;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "mobile_number")
    private String mobileNumber;
}
