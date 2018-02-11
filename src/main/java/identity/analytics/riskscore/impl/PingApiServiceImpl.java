package identity.analytics.riskscore.impl;

import identity.analytics.riskscore.*;
import identity.analytics.riskscore.dto.*;



import java.util.List;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class PingApiServiceImpl extends PingApiService {
    private static final Log log = LogFactory.getLog(PingApiServiceImpl.class);

    @Override
    public Response pingGet(){
        // do some magic!
        log.info("DO MAGIC");
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
