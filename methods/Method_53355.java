@Override public ResponseList<UserList> getUserLists(String listOwnerScreenName,boolean reverse) throws TwitterException {
  return factory.createUserListList(get(conf.getRestBaseURL() + "lists/list.json",new HttpParameter("screen_name",listOwnerScreenName),new HttpParameter("reverse",reverse)));
}
