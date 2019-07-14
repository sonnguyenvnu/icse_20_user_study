@Override public ResponseList<SavedSearch> getSavedSearches() throws TwitterException {
  return factory.createSavedSearchList(get(conf.getRestBaseURL() + "saved_searches/list.json"));
}
