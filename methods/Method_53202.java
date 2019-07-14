static URLEntity[] getUrls(JSONObject entities) throws JSONException, TwitterException {
  if (!entities.isNull("urls")) {
    JSONArray urlsArray=entities.getJSONArray("urls");
    int len=urlsArray.length();
    URLEntity[] urlEntities=new URLEntity[len];
    for (int i=0; i < len; i++) {
      urlEntities[i]=new URLEntityJSONImpl(urlsArray.getJSONObject(i));
    }
    return urlEntities;
  }
 else {
    return null;
  }
}
