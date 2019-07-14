@Override public ResponseList<Status> getFavorites() throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "favorites/list.json"));
}
