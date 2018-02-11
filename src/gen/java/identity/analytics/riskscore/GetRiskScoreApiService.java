package identity.analytics.riskscore;

import identity.analytics.riskscore.*;
import identity.analytics.riskscore.dto.*;

import identity.analytics.riskscore.dto.ErrorModelDTO;
import identity.analytics.riskscore.dto.AuthRequestDTO;
import identity.analytics.riskscore.dto.RiskScoreDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class GetRiskScoreApiService {
    public abstract Response getRiskScore(AuthRequestDTO authRequest);
}

