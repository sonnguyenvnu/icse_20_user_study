/** 
 * Updates/Adds indexing the specified document in Algolia.
 * @param doc the specified document
 */
public void updateAlgoliaDocument(final JSONObject doc){
  final int maxRetries=3;
  int retries=1;
  final String appId=Symphonys.ALGOLIA_APP_ID;
  final String index=Symphonys.ALGOLIA_INDEX;
  final String key=Symphonys.ALGOLIA_ADMIN_KEY;
  while (retries <= maxRetries) {
    String host=appId + "-" + retries + ".algolianet.com";
    try {
      final String id=doc.optString(Keys.OBJECT_ID);
      String content=doc.optString(Article.ARTICLE_CONTENT);
      content=Markdowns.toHTML(content);
      content=Jsoup.parse(content).text();
      doc.put(Article.ARTICLE_CONTENT,content);
      final long dataLength=Utf8.size(doc.toString());
      final int maxLength=9000;
      if (dataLength >= maxLength) {
        LOGGER.log(Level.INFO,"This article [id=" + id + "] is too big [length=" + dataLength + "], so cuts it");
        final int length=content.length();
        int idx=length;
        int continueCnt=0;
        while (idx > 0) {
          idx-=128;
          content=content.substring(0,idx);
          if (Utf8.size(content) < maxLength) {
            continueCnt++;
          }
          if (3 < continueCnt) {
            break;
          }
        }
        doc.put(Article.ARTICLE_CONTENT,content);
      }
      final byte[] data=doc.toString().getBytes(StandardCharsets.UTF_8);
      final HttpResponse response=HttpRequest.put("https://" + host + "/1/indexes/" + index + "/" + id).header("X-Algolia-API-Key",key).header("X-Algolia-Application-Id",appId).body(data,MimeTypes.MIME_APPLICATION_JSON).connectionTimeout(5000).timeout(5000).send();
      response.charset("UTF-8");
      if (200 != response.statusCode()) {
        LOGGER.warn(response.bodyText());
      }
      break;
    }
 catch (    final Exception e) {
      LOGGER.log(Level.WARN,"Index failed",e);
      retries++;
      if (retries > maxRetries) {
        LOGGER.log(Level.ERROR,"Index failed [doc=" + doc + "]",e);
      }
    }
    try {
      Thread.sleep(100);
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Sleep error",e);
    }
  }
}
