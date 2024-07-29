package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.model.entity.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SimCardRepo extends JpaRepository<SimCard, Long> {
}
