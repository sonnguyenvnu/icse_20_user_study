@Override public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/retweets_of_me.json"));
}
