static UserMentionEntity[] getUserMentions(JSONObject entities) throws JSONException, TwitterException {
  if (!entities.isNull("user_mentions")) {
    JSONArray userMentionsArray=entities.getJSONArray("user_mentions");
    int len=userMentionsArray.length();
    UserMentionEntity[] userMentionEntities=new UserMentionEntity[len];
    for (int i=0; i < len; i++) {
      userMentionEntities[i]=new UserMentionEntityJSONImpl(userMentionsArray.getJSONObject(i));
    }
    return userMentionEntities;
  }
 else {
    return null;
  }
}
