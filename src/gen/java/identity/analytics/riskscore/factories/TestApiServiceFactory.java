package identity.analytics.riskscore.factories;

import identity.analytics.riskscore.TestApiService;
import identity.analytics.riskscore.impl.TestApiServiceImpl;

public class TestApiServiceFactory {

   private final static TestApiService service = new TestApiServiceImpl();

   public static TestApiService getTestApi()
   {
      return service;
   }
}
