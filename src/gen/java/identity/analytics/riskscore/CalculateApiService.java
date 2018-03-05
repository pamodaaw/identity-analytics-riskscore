package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.AuthRequestDTO;

import javax.ws.rs.core.Response;

public abstract class CalculateApiService {
    public abstract Response calculateRiskScore(AuthRequestDTO authRequest);
}

