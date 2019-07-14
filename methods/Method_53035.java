/** 
 * Returns the response body as twitter4j.JSONObject.<br> Disconnects the internal HttpURLConnection silently.
 * @return response body as twitter4j.JSONObject
 * @throws TwitterException when the response body is not in JSON Object format
 */
public JSONObject asJSONObject() throws TwitterException {
  if (json == null) {
    try {
      json=new JSONObject(asString());
      if (CONF.isPrettyDebugEnabled()) {
        logger.debug(json.toString(1));
      }
 else {
        logger.debug(responseAsString != null ? responseAsString : json.toString());
      }
    }
 catch (    JSONException jsone) {
      if (responseAsString == null) {
        throw new TwitterException(jsone.getMessage(),jsone);
      }
 else {
        throw new TwitterException(jsone.getMessage() + ":" + this.responseAsString,jsone);
      }
    }
 finally {
      disconnectForcibly();
    }
  }
  return json;
}
