@Override void onFavoritedRetweet(JSONObject source,JSONObject target,JSONObject targetObject,StreamListener[] listeners) throws TwitterException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onFavoritedRetweet(asUser(source),asUser(target),asStatus(targetObject));
  }
}
