@Override protected void onFriends(JSONObject json,StreamListener[] listeners) throws TwitterException, JSONException {
  long[] friendIds=asFriendList(json);
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onFriendList(friendIds);
  }
}
