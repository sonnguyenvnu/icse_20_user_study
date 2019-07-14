@Override protected void onUserListCreation(JSONObject source,JSONObject target,StreamListener[] listeners) throws TwitterException, JSONException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onUserListCreation(asUser(source),asUserList(target));
  }
}
