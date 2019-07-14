@Override public IDs getOutgoingFriendships(long cursor) throws TwitterException {
  return factory.createIDs(get(conf.getRestBaseURL() + "friendships/outgoing.json?cursor=" + cursor));
}
