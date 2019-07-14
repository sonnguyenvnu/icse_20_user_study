@Override public ResponseList<Status> getFavorites(long userId) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "favorites/list.json?user_id=" + userId));
}
