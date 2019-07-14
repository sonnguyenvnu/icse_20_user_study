@Override public ResponseList<Status> getRetweets(long statusId) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/retweets/" + statusId + ".json?count=100"));
}
