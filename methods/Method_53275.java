@Override public Status retweetStatus(long statusId) throws TwitterException {
  return factory.createStatus(post(conf.getRestBaseURL() + "statuses/retweet/" + statusId + ".json"));
}
