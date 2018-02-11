package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.*;
import identity.analytics.riskscore.GetRiskScoreApiService;
import identity.analytics.riskscore.factories.GetRiskScoreApiServiceFactory;

import io.swagger.annotations.ApiParam;

import identity.analytics.riskscore.dto.ErrorModelDTO;
import identity.analytics.riskscore.dto.AuthRequestDTO;
import identity.analytics.riskscore.dto.RiskScoreDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/getRiskScore")


@io.swagger.annotations.Api(value = "/getRiskScore", description = "the getRiskScore API")
public class GetRiskScoreApi  {

   private final GetRiskScoreApiService delegate = GetRiskScoreApiServiceFactory.getGetRiskScoreApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Method to obtain the calculated risk score an authentication request", response = RiskScoreDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Risk Score"),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "unexpected error") })

    public Response getRiskScore(@ApiParam(value = "authentication request by the user" ,required=true ) AuthRequestDTO authRequest)
    {
    return delegate.getRiskScore(authRequest);
    }
}

