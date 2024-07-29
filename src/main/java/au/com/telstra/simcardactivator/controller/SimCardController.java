package au.com.telstra.simcardactivator.controller;


import au.com.telstra.simcardactivator.model.dto.SimCardRequest;
import au.com.telstra.simcardactivator.service.SimCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimCardController {
    private final SimCardService simCardService;

    @PostMapping("/activate-sim")
    ResponseEntity<String> activateSim(@RequestBody SimCardRequest simCardRequest) {

        String iccid = simCardRequest.getIccid();
        log.info("SIM controller, method activate SIM for iccid = {}", iccid);
        return ResponseEntity.ok(simCardService.activateSim(simCardRequest));
    }

    @GetMapping("/sim-card")
    ResponseEntity <SimCardRequest> getSimCardInfo(@RequestParam("simCardId") Long simCardId){
        log.info("SIM controller, method get SIM information for id = {}", simCardId);
        return ResponseEntity.ok(simCardService.getSimCardInfo(simCardId));
    }
}
