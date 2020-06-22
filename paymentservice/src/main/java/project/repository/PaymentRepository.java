package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.otus.model.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
