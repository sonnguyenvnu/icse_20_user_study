@Override protected void onUserListUpdated(JSONObject source,JSONObject target,StreamListener[] listeners) throws TwitterException, JSONException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onUserListUpdate(asUser(source),asUserList(target));
  }
}
