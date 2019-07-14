@Override protected void onQuotedTweet(JSONObject source,JSONObject target,JSONObject targetObject,StreamListener[] listeners) throws TwitterException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onQuotedTweet(asUser(source),asUser(target),asStatus(targetObject));
  }
}
