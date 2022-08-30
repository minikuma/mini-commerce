package me.minikuma.repository;

import me.minikuma.domain.customer.Customer;
import me.minikuma.domain.payment.Payment;
import me.minikuma.repository.address.AddressRepository;
import me.minikuma.repository.customer.CustomerRepository;
import me.minikuma.repository.payment.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CustomerRepositoryTest {
    private static final Logger log = (Logger) LoggerFactory.getLogger(CustomerRepositoryTest.class);

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Test
    @DisplayName("고객 정보를 저장 한다.(payment, customer 연관 관계 맵핑 검증")
    void customer_save() {
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

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(payment);
        Assertions.assertEquals(customer.getPayment().getPaymentId(), payment.getPaymentId());
    }
}