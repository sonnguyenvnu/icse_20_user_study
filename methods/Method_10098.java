/** 
 * Removes the specified document in ES.
 * @param doc  the specified document
 * @param type the specified document type
 */
public void removeESDocument(final JSONObject doc,final String type){
  try {
    HttpRequest.delete(Symphonys.ES_SERVER + "/" + ES_INDEX_NAME + "/" + type + "/" + doc.optString(Keys.OBJECT_ID)).timeout(5000).sendAsync();
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Updates doc failed",e);
  }
}
