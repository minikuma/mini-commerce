package me.minikuma.repository;

import me.minikuma.domain.address.Address;
import me.minikuma.domain.customer.Customer;
import me.minikuma.domain.payment.Payment;
import me.minikuma.repository.customer.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CustomerRepositoryTest {
    private static final Logger log = (Logger) LoggerFactory.getLogger(CustomerRepositoryTest.class);

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @DisplayName("고객 정보를 저장 한다.(payment, customer 연관 관계 맵핑 검증")
    void customer_payment_save() {
        Customer customer = Customer.builder()
                .phoneNumber("010-2222-3333")
                .userName("username")
                .passWord("password")
                .build();

        Payment payment = Payment.builder()
                .paymentType("신용카드")
                .expiry("2022/12/25")
                .accountNumber("123-044440-3333")
                .provider("google")
                .build();

        customer.changePayment(payment); // 연관 관계 맵핑
        customerRepository.save(customer);

        assertNotNull(customer);
        assertNotNull(payment);
        assertEquals(customer.getPayment().getPaymentId(), payment.getPaymentId());
    }

    @Test
    @DisplayName("고객 정보를 저장 한다. (address, customer 연관 관계 매핑 검증)")
    public void customer_address_save() {
        // 고객 정보
        Customer customer = Customer.builder()
                .userName("username_1")
                .passWord("password_1")
                .phoneNumber("010-2345-1234")
                .build();

        // 주소 정보
        Address address = Address.builder()
                .zipCode("123-4566")
                .mobileNumber("010-9990-3333")
                .phoneNumber("")
                .build();

        customer.setAddress(address);

        customerRepository.save(customer);

        assertNotNull(customer);
        assertNotNull(address);
        assertEquals(customer.getAddress().getAddressId(), address.getAddressId());
    }

    @Test
    @DisplayName("Customer 정보 조회 (연관 관계 정보까지 전체 조회)")
    void selectCustomer() {
        // 사전 준비
        Customer customer = Customer.builder()
                .userName("username_2")
                .passWord("password_2")
                .phoneNumber("010-7777-3333")
                .build();

        Address address = Address.builder()
                .zipCode("123-4567")
                .mobileNumber("010-7777-3333")
                .phoneNumber("")
                .build();

        Payment payment = Payment.builder()
                .paymentType("직불")
                .accountNumber("322-134345-1233")
                .expiry("22/12/31")
                .provider("하나")
                .build();

        customer.setAddress(address);
        customer.setPayment(payment);

        customerRepository.save(customer);

        // 조회
        Optional<Customer> findCustomerById = customerRepository.findById(customer.getCustomerId());
        findCustomerById.ifPresent(value -> {
            assertEquals(value.getCustomerId(), customer.getCustomerId());
            assertEquals(value.getPassWord(), customer.getPassWord());
            assertEquals(value.getUserName(), customer.getUserName());
            assertEquals(value.getAddress().getAddressId(), address.getAddressId());
            assertEquals(value.getPayment().getPaymentId(), payment.getPaymentId());
        });
    }
}