@Override public User destroyFriendship(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "friendships/destroy.json?user_id=" + userId));
}
