@Override public ResponseList<Status> getUserTimeline(Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/user_timeline.json",mergeParameters(new HttpParameter[]{INCLUDE_MY_RETWEET},paging.asPostParameterArray())));
}
