@Override protected void onUserListMemberAddition(JSONObject addedMember,JSONObject owner,JSONObject target,StreamListener[] listeners) throws TwitterException, JSONException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onUserListMemberAddition(asUser(addedMember),asUser(owner),asUserList(target));
  }
}
