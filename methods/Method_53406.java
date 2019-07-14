@Override public User reportSpam(long userId) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "users/report_spam.json?user_id=" + userId));
}
