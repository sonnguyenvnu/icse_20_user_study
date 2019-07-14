@Override public User createFriendship(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "friendships/create.json?user_id=" + userId));
}
