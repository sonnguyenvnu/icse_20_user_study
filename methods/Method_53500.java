@Override protected void onUnfollow(JSONObject source,JSONObject target,StreamListener[] listeners) throws TwitterException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onUnfollow(asUser(source),asUser(target));
  }
}
