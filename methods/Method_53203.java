static HashtagEntity[] getHashtags(JSONObject entities) throws JSONException, TwitterException {
  if (!entities.isNull("hashtags")) {
    JSONArray hashtagsArray=entities.getJSONArray("hashtags");
    int len=hashtagsArray.length();
    HashtagEntity[] hashtagEntities=new HashtagEntity[len];
    for (int i=0; i < len; i++) {
      hashtagEntities[i]=new HashtagEntityJSONImpl(hashtagsArray.getJSONObject(i));
    }
    return hashtagEntities;
  }
 else {
    return null;
  }
}
