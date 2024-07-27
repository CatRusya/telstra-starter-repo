package au.com.telstra.simcardactivator.controller;


import au.com.telstra.simcardactivator.model.dto.ActuatorResponse;
import au.com.telstra.simcardactivator.model.entity.SIMRequest;
import au.com.telstra.simcardactivator.service.SIMService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SIMController {
    private final SIMService simService;

    @PostMapping()
    ResponseEntity<String> activateSim(@RequestBody SIMRequest simRequest) {

        String iccid = simRequest.getIccid();
        log.info("SIM controller, method activate SIM for iccid = {}", iccid);
        return ResponseEntity.ok(simService.activateSim(iccid));
    }
}
