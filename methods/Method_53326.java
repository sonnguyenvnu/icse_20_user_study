@Override public User destroyBlock(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "blocks/destroy.json?user_id=" + userId));
}
