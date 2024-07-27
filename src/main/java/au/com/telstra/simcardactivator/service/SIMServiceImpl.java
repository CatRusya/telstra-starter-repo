package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.dto.ActuatorRequest;
import au.com.telstra.simcardactivator.model.dto.ActuatorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SIMServiceImpl implements SIMService {
    @Value("${variables.actuator-url}")
    private String actuator_url;

    private final RestTemplate restTemplate;

    @Override
    public String activateSim(String iccid) {
        ActuatorRequest actuatorRequest = new ActuatorRequest(iccid);
        if (iccid.isEmpty()) {
            return "SIM iccid is empty";
        }
        ResponseEntity<ActuatorResponse> response =
                restTemplate.postForEntity(actuator_url, actuatorRequest, ActuatorResponse.class);
        return Objects.requireNonNull(response.getBody()).getIsSuccess().toString();
    }
}
