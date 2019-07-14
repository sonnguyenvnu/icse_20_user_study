@Override public ResponseList<HelpResources.Language> createLanguageList(final HttpResponse res) throws TwitterException {
  return new LazyResponseList<HelpResources.Language>(){
    @Override protected ResponseList<HelpResources.Language> createActualResponseList() throws TwitterException {
      return LanguageJSONImpl.createLanguageList(res,conf);
    }
  }
;
}
