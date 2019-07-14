@Override public ResponseList<Status> getFavorites(String screenName,Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "favorites/list.json",mergeParameters(new HttpParameter[]{new HttpParameter("screen_name",screenName)},paging.asPostParameterArray())));
}
