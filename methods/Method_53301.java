@Override public User createFriendship(long userId,boolean follow) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "friendships/create.json?user_id=" + userId + "&follow=" + follow));
}
