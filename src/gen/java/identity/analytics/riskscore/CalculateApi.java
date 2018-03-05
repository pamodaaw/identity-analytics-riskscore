package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.AuthRequestDTO;
import identity.analytics.riskscore.dto.RiskScoreDTO;
import identity.analytics.riskscore.factories.CalculateApiServiceFactory;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/calculate")


@io.swagger.annotations.Api(value = "/calculate", description = "the calculate API")
public class CalculateApi {

    private final CalculateApiService delegate = CalculateApiServiceFactory.getCalculateApi();

    @POST

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "", notes = "Method to obtain the calculated risk score an " +
            "authentication request", response = RiskScoreDTO.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Risk Score"),

            @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error")})

    public Response calculateRiskScore(@ApiParam(value = "authentication request by the user", required = true)
                                                   AuthRequestDTO authRequest) {
        return delegate.calculateRiskScore(authRequest);
    }
}

