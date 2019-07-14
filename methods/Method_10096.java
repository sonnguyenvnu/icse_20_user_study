/** 
 * Rebuilds Algolia index.
 */
public void rebuildAlgoliaIndex(){
  final int maxRetries=3;
  int retries=1;
  final String appId=Symphonys.ALGOLIA_APP_ID;
  final String index=Symphonys.ALGOLIA_INDEX;
  final String key=Symphonys.ALGOLIA_ADMIN_KEY;
  while (retries <= maxRetries) {
    String host=appId + "-" + retries + ".algolianet.com";
    try {
      final HttpResponse response=HttpRequest.post("https://" + host + "/1/indexes/" + index + "/clear").header("X-Algolia-API-Key",key).header("X-Algolia-Application-Id",appId).timeout(5000).send();
      if (200 != response.statusCode()) {
        LOGGER.warn(response.toString());
      }
      break;
    }
 catch (    final Exception e) {
      LOGGER.log(Level.WARN,"Clear index failed",e);
      retries++;
      if (retries > maxRetries) {
        LOGGER.log(Level.ERROR,"Clear index failed",e);
      }
    }
  }
}
