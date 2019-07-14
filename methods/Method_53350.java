@Override public ResponseList<Status> getFavorites(Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "favorites/list.json",paging.asPostParameterArray()));
}
