UserList asUserList(JSONObject json) throws TwitterException {
  UserList userList=new UserListJSONImpl(json);
  if (CONF.isJSONStoreEnabled()) {
    TwitterObjectFactory.registerJSONObject(userList,json);
  }
  return userList;
}
