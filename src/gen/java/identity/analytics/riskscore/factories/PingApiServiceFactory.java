package identity.analytics.riskscore.factories;

import identity.analytics.riskscore.PingApiService;
import identity.analytics.riskscore.impl.PingApiServiceImpl;

public class PingApiServiceFactory {

    private final static PingApiService service = new PingApiServiceImpl();

    public static PingApiService getPingApi() {
        return service;
    }
}
