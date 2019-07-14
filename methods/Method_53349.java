@Override public ResponseList<Status> getFavorites(String screenName) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "favorites/list.json",new HttpParameter("screen_name",screenName)));
}
