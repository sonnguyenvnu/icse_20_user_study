@Override public Status destroyStatus(long statusId) throws TwitterException {
  return factory.createStatus(post(conf.getRestBaseURL() + "statuses/destroy/" + statusId + ".json"));
}
