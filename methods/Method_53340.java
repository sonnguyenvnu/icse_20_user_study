@Override public ResponseList<User> getContributors(long userId) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/contributors.json?user_id=" + userId));
}
