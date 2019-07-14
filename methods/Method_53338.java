@Override public ResponseList<User> getContributees(long userId) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/contributees.json?user_id=" + userId));
}
