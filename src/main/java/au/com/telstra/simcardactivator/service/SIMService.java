package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.dto.ActuatorResponse;

public interface SIMService {

    String activateSim(String iccid);
}
