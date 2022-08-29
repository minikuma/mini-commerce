package me.minikuma.repository;

import me.minikuma.domain.address.Address;
import me.minikuma.domain.customer.Customer;
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

    @Test
    @DisplayName("고객 정보를 저장 한다.")
    void customer_save() {
        Address newAddress = Address.builder()
                .addressId(11L)
                .customerId(1L)
                .zipCode("123-3333")
                .phoneNumber("")
                .mobileNumber("010-1111-2222")
                .build();

        addressRepository.save(newAddress);

        Customer customer = Customer.builder()
                .phoneNumber("010-2222-3333")
                .customerId(newAddress.getCustomerId())
                .userName("username")
                .passWord("password")
                .build();

        customerRepository.save(customer);

        log.info("customer saved successfully :: {}", customer.getCustomerId());
    }
}