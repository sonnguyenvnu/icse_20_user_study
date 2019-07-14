@Override protected void onUserListMemberDeletion(JSONObject deletedMember,JSONObject owner,JSONObject target,StreamListener[] listeners) throws TwitterException, JSONException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onUserListMemberDeletion(asUser(deletedMember),asUser(owner),asUserList(target));
  }
}
