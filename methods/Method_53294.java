@Override public IDs getNoRetweetsFriendships() throws TwitterException {
  return factory.createIDs(get(conf.getRestBaseURL() + "friendships/no_retweets/ids.json"));
}
