static MediaEntity[] getMedia(JSONObject entities) throws JSONException, TwitterException {
  if (!entities.isNull("media")) {
    JSONArray mediaArray=entities.getJSONArray("media");
    int len=mediaArray.length();
    MediaEntity[] mediaEntities=new MediaEntity[len];
    for (int i=0; i < len; i++) {
      mediaEntities[i]=new MediaEntityJSONImpl(mediaArray.getJSONObject(i));
    }
    return mediaEntities;
  }
 else {
    return null;
  }
}
