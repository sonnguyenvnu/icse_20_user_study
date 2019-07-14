/** 
 * Removes the specified document in Algolia.
 * @param doc the specified document
 */
public void removeAlgoliaDocument(final JSONObject doc){
  final int maxRetries=3;
  int retries=1;
  final String appId=Symphonys.ALGOLIA_APP_ID;
  final String index=Symphonys.ALGOLIA_INDEX;
  final String key=Symphonys.ALGOLIA_ADMIN_KEY;
  while (retries <= maxRetries) {
    String host=appId + "-" + retries + ".algolianet.com";
    try {
      final String id=doc.optString(Keys.OBJECT_ID);
      final HttpResponse response=HttpRequest.delete("https://" + host + "/1/indexes/" + index + "/" + id).header("X-Algolia-API-Key",key).header("X-Algolia-Application-Id",appId).bodyText(doc.toString()).contentTypeJson().timeout(5000).send();
      if (200 != response.statusCode()) {
        LOGGER.warn(response.toString());
      }
      break;
    }
 catch (    final Exception e) {
      LOGGER.log(Level.WARN,"Remove object failed",e);
      retries++;
      if (retries > maxRetries) {
        LOGGER.log(Level.ERROR,"Remove object failed",e);
      }
    }
  }
}
