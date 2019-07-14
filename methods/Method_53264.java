@Override public ResponseList<Status> getHomeTimeline(Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/home_timeline.json",mergeParameters(paging.asPostParameterArray(),new HttpParameter[]{INCLUDE_MY_RETWEET})));
}
