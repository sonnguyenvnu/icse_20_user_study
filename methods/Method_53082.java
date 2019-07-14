static ResponseList<HelpResources.Language> createLanguageList(HttpResponse res,Configuration conf) throws TwitterException {
  return createLanguageList(res.asJSONArray(),res,conf);
}
