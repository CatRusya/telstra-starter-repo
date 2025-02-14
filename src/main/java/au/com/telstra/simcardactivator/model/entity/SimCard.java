package au.com.telstra.simcardactivator.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simCardId;

    @Column(nullable = false)
    private String iccid;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Boolean isSuccess;
}
