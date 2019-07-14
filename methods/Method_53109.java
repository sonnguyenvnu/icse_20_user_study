private void mergeExtendedEntities(JSONObject json) throws JSONException, TwitterException {
  if (!json.isNull("extended_entities")) {
    JSONObject extendedEntities=json.getJSONObject("extended_entities");
    if (!extendedEntities.isNull("media")) {
      JSONArray mediaArray=extendedEntities.getJSONArray("media");
      final int len=mediaArray.length();
      mediaEntities=new MediaEntity[len];
      for (int i=0; i < len; i++) {
        mediaEntities[i]=new MediaEntityJSONImpl(mediaArray.getJSONObject(i));
      }
    }
  }
}
