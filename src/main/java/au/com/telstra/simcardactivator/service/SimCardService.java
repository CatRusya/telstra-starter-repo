package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.dto.SimCardRequest;

public interface SimCardService {

    String activateSim(SimCardRequest simCardRequest);
    SimCardRequest getSimCardInfo(Long simCardId);
}
