@Override public ResponseList<User> getContributors(String screenName) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/contributors.json",new HttpParameter("screen_name",screenName)));
}
