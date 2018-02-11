package identity.analytics.riskscore;

import identity.analytics.riskscore.dto.*;
import identity.analytics.riskscore.TestApiService;
import identity.analytics.riskscore.factories.TestApiServiceFactory;

import io.swagger.annotations.ApiParam;


import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/test")


@io.swagger.annotations.Api(value = "/test", description = "the test API")
public class TestApi  {

   private final TestApiService delegate = TestApiServiceFactory.getTestApi();

    @POST
    
    
    
    @io.swagger.annotations.ApiOperation(value = "test post request", notes = "", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created") })

    public Response testPost()
    {
    return delegate.testPost();
    }
}

