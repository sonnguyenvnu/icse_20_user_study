@Override public ResponseList<Status> getFavorites(long userId,Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "favorites/list.json",mergeParameters(new HttpParameter[]{new HttpParameter("user_id",userId)},paging.asPostParameterArray())));
}
