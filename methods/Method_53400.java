@Override public SavedSearch showSavedSearch(long id) throws TwitterException {
  return factory.createSavedSearch(get(conf.getRestBaseURL() + "saved_searches/show/" + id + ".json"));
}
