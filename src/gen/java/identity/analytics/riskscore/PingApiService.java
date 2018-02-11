package identity.analytics.riskscore;

import identity.analytics.riskscore.*;
import identity.analytics.riskscore.dto.*;


import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class PingApiService {
    public abstract Response pingGet();
}

