@Override public ResponseList<Status> getUserTimeline(String screenName,Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/user_timeline.json",mergeParameters(new HttpParameter[]{new HttpParameter("screen_name",screenName),INCLUDE_MY_RETWEET},paging.asPostParameterArray())));
}
