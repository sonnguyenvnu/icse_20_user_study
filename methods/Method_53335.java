@Override public User showUser(long userId) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "users/show.json?user_id=" + userId));
}
