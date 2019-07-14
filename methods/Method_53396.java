@Override public PagableResponseList<UserList> getUserListsOwnerships(String listOwnerScreenName,int count,long cursor) throws TwitterException {
  return factory.createPagableUserListList(get(conf.getRestBaseURL() + "lists/ownerships.json",new HttpParameter("screen_name",listOwnerScreenName),new HttpParameter("count",count),new HttpParameter("cursor",cursor)));
}
