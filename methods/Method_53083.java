static ResponseList<HelpResources.Language> createLanguageList(JSONArray list,HttpResponse res,Configuration conf) throws TwitterException {
  if (conf.isJSONStoreEnabled()) {
    TwitterObjectFactory.clearThreadLocalMap();
  }
  try {
    int size=list.length();
    ResponseList<HelpResources.Language> languages=new ResponseListImpl<HelpResources.Language>(size,res);
    for (int i=0; i < size; i++) {
      JSONObject json=list.getJSONObject(i);
      HelpResources.Language language=new LanguageJSONImpl(json);
      languages.add(language);
      if (conf.isJSONStoreEnabled()) {
        TwitterObjectFactory.registerJSONObject(language,json);
      }
    }
    if (conf.isJSONStoreEnabled()) {
      TwitterObjectFactory.registerJSONObject(languages,list);
    }
    return languages;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
