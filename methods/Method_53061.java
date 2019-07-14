@Override public UserList createAUserList(HttpResponse res) throws TwitterException {
  return new UserListJSONImpl(res,conf);
}
