static ResponseList<SavedSearch> createSavedSearchList(HttpResponse res,Configuration conf) throws TwitterException {
  if (conf.isJSONStoreEnabled()) {
    TwitterObjectFactory.clearThreadLocalMap();
  }
  JSONArray json=res.asJSONArray();
  ResponseList<SavedSearch> savedSearches;
  try {
    savedSearches=new ResponseListImpl<SavedSearch>(json.length(),res);
    for (int i=0; i < json.length(); i++) {
      JSONObject savedSearchesJSON=json.getJSONObject(i);
      SavedSearch savedSearch=new SavedSearchJSONImpl(savedSearchesJSON);
      savedSearches.add(savedSearch);
      if (conf.isJSONStoreEnabled()) {
        TwitterObjectFactory.registerJSONObject(savedSearch,savedSearchesJSON);
      }
    }
    if (conf.isJSONStoreEnabled()) {
      TwitterObjectFactory.registerJSONObject(savedSearches,json);
    }
    return savedSearches;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone.getMessage() + ":" + res.asString(),jsone);
  }
}
