@Override public User destroyMute(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "mutes/users/destroy.json?user_id=" + userId));
}
