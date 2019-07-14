@Override public ResponseList<Status> getMentionsTimeline() throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/mentions_timeline.json"));
}
