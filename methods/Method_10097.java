/** 
 * Updates/Adds indexing the specified document in ES.
 * @param doc  the specified document
 * @param type the specified document type
 */
public void updateESDocument(final JSONObject doc,final String type){
  try {
    final JSONObject payload=new JSONObject();
    payload.put("doc",doc);
    payload.put("upsert",doc);
    HttpRequest.post(Symphonys.ES_SERVER + "/" + ES_INDEX_NAME + "/" + type + "/" + doc.optString(Keys.OBJECT_ID) + "/_update").bodyText(payload.toString()).contentTypeJson().timeout(5000).sendAsync();
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Updates doc failed",e);
  }
}
