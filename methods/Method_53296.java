@Override public ResponseList<Friendship> lookupFriendships(String... screenNames) throws TwitterException {
  return factory.createFriendshipList(get(conf.getRestBaseURL() + "friendships/lookup.json?screen_name=" + StringUtil.join(screenNames)));
}
