@Override public ResponseList<UserList> getUserLists(long listOwnerUserId,boolean reverse) throws TwitterException {
  return factory.createUserListList(get(conf.getRestBaseURL() + "lists/list.json",new HttpParameter("user_id",listOwnerUserId),new HttpParameter("reverse",reverse)));
}
