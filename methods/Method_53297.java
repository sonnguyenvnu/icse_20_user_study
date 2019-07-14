@Override public IDs getIncomingFriendships(long cursor) throws TwitterException {
  return factory.createIDs(get(conf.getRestBaseURL() + "friendships/incoming.json?cursor=" + cursor));
}
