package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.dto.ActuatorRequest;
import au.com.telstra.simcardactivator.model.dto.ActuatorResponse;
import au.com.telstra.simcardactivator.model.dto.SimCardRequest;
import au.com.telstra.simcardactivator.model.entity.SimCard;
import au.com.telstra.simcardactivator.repository.SimCardRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimCardServiceImpl implements SimCardService {
    @Value("${variables.actuator-url}")
    private String actuator_url;

    private final RestTemplate restTemplate;
    private final SimCardRepo simCardRepo;

    @Override
    public String activateSim(SimCardRequest simCardRequest) {
        String iccid = simCardRequest.getIccid();
        if (iccid.isEmpty()) {
            return "SIM iccid is empty";
        }
        ActuatorRequest actuatorRequest = new ActuatorRequest(iccid);
        ResponseEntity<ActuatorResponse> response =
                restTemplate.postForEntity(actuator_url, actuatorRequest, ActuatorResponse.class);
        if (response.getBody() != null)  {
            saveActivationRecord(iccid, simCardRequest.getEmail(), response.getBody().isSuccess());
            return String.valueOf(response.getBody().isSuccess());
        } else {
            throw new RuntimeException("Failed to get a response from the actuator service");
        }
    }

    @Override
    public SimCardRequest getSimCardInfo(Long simCardId) {
        SimCard simCard = getSimCard(simCardId);
        return SimCardRequest.builder()
                .iccid(simCard.getIccid())
                .email(simCard.getEmail())
                .isSuccess(simCard.getIsSuccess())
                .build();
    }

    private void saveActivationRecord(String iccid, String email, boolean success) {
        SimCard simCard = SimCard.builder()
                .iccid(iccid)
                .email(email)
                .isSuccess(success)
                .build();
        simCardRepo.save(simCard);
    }

    private SimCard getSimCard(Long simCardId) {
        return simCardRepo.findById(simCardId).orElseThrow(
                () -> new EntityNotFoundException("SIM not found"));
    }
}
