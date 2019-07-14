@Override public ResponseList<Status> getRetweetsOfMe(Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/retweets_of_me.json",paging.asPostParameterArray()));
}
