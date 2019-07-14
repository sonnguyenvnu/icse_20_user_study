@Override public ResponseList<Status> getMentionsTimeline(Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/mentions_timeline.json",paging.asPostParameterArray()));
}
