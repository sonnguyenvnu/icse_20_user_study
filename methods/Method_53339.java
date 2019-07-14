@Override public ResponseList<User> getContributees(String screenName) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/contributees.json",new HttpParameter("screen_name",screenName)));
}
