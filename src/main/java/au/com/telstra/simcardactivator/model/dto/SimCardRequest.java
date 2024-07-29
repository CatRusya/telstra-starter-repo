package au.com.telstra.simcardactivator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimCardRequest {

    private String iccid;
    private String email;
    private boolean isSuccess;

}
