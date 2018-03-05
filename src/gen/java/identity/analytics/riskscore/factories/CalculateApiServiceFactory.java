package identity.analytics.riskscore.factories;

import identity.analytics.riskscore.CalculateApiService;
import identity.analytics.riskscore.impl.CalculateApiServiceImpl;

public class CalculateApiServiceFactory {

    private final static CalculateApiService service = new CalculateApiServiceImpl();

    public static CalculateApiService getCalculateApi() {
        return service;
    }
}
