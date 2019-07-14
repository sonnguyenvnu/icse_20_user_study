@Override public ResponseList<User> lookupUsers(String... screenNames) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/lookup.json",new HttpParameter("screen_name",StringUtil.join(screenNames))));
}
