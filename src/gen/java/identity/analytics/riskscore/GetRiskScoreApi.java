package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.AuthRequestDTO;
import identity.analytics.riskscore.dto.RiskScoreDTO;
import identity.analytics.riskscore.factories.GetRiskScoreApiServiceFactory;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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

            @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error")})

    public Response getRiskScore(@ApiParam(value = "authentication request by the user" ,required=true ) AuthRequestDTO authRequest)
    {
    return delegate.getRiskScore(authRequest);
    }
}

