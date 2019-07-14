@Override public User createMute(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "mutes/users/create.json?user_id=" + userId));
}
