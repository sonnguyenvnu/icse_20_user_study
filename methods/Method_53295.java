@Override public ResponseList<Friendship> lookupFriendships(long... ids) throws TwitterException {
  return factory.createFriendshipList(get(conf.getRestBaseURL() + "friendships/lookup.json?user_id=" + StringUtil.join(ids)));
}
