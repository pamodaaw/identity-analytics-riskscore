package identity.analytics.riskscore.factories;

import identity.analytics.riskscore.GetRiskScoreApiService;
import identity.analytics.riskscore.impl.GetRiskScoreApiServiceImpl;

public class GetRiskScoreApiServiceFactory {

   private final static GetRiskScoreApiService service = new GetRiskScoreApiServiceImpl();

   public static GetRiskScoreApiService getGetRiskScoreApi()
   {
      return service;
   }
}
