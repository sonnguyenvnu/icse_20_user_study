@Override public Status unRetweetStatus(long statusId) throws TwitterException {
  return factory.createStatus(post(conf.getRestBaseURL() + "statuses/unretweet/" + statusId + ".json"));
}
