@Override public ResponseList<User> lookupUsers(long... ids) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/lookup.json",new HttpParameter("user_id",StringUtil.join(ids))));
}
