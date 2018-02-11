package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.*;
import identity.analytics.riskscore.PingApiService;
import identity.analytics.riskscore.factories.PingApiServiceFactory;

import io.swagger.annotations.ApiParam;


import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.context.annotation.Bean;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/ping")


@io.swagger.annotations.Api(value = "/ping", description = "the ping API")
public class PingApi  {

   private final PingApiService delegate = PingApiServiceFactory.getPingApi();

    @GET
    @Bean
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK") })

    public Response pingGet()
    {
    return delegate.pingGet();
    }
}

