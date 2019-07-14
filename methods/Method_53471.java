long[] asFriendList(JSONObject json) throws TwitterException {
  JSONArray friends;
  try {
    friends=json.getJSONArray("friends");
    long[] friendIds=new long[friends.length()];
    for (int i=0; i < friendIds.length; ++i) {
      friendIds[i]=Long.parseLong(friends.getString(i));
    }
    return friendIds;
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
