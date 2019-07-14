@Override public User createBlock(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "blocks/create.json?user_id=" + userId));
}
