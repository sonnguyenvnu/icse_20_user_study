@Override public ResponseList<Status> lookup(long... ids) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/lookup.json?id=" + StringUtil.join(ids)));
}
