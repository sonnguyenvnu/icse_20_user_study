User asUser(JSONObject json) throws TwitterException {
  User user=new UserJSONImpl(json);
  if (CONF.isJSONStoreEnabled()) {
    TwitterObjectFactory.registerJSONObject(user,json);
  }
  return user;
}
