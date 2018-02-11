package identity.analytics.riskscore.impl;

import identity.analytics.riskscore.*;
import identity.analytics.riskscore.dto.*;


import java.util.List;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class TestingPOST extends GetRiskScoreApiService {
    private static final Log log = LogFactory.getLog(PingApiServiceImpl.class);

    @Override
    public Response getRiskScore(AuthRequestDTO authRequest) {
        // do some magic!
        return Response.ok().entity(new RiskScoreDTO("testevent", 23)).build();
    }

}
