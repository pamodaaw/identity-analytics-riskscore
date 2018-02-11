package identity.analytics.riskscore.impl;

import identity.analytics.riskscore.*;
import identity.analytics.riskscore.dto.*;



import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class TestApiServiceImpl extends TestApiService {
    @Override
    public Response testPost(){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
