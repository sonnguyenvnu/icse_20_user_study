@Override protected void onUnfavorite(JSONObject source,JSONObject target,JSONObject targetObject,StreamListener[] listeners) throws TwitterException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onUnfavorite(asUser(source),asUser(target),asStatus(targetObject));
  }
}
