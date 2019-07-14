DirectMessage asDirectMessage(JSONObject json) throws TwitterException {
  try {
    JSONObject dmJSON=json.getJSONObject("direct_message");
    DirectMessage directMessage=new DirectMessageJSONImpl(dmJSON);
    if (CONF.isJSONStoreEnabled()) {
      TwitterObjectFactory.registerJSONObject(directMessage,dmJSON);
    }
    return directMessage;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
