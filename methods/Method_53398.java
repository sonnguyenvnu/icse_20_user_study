@Override public PagableResponseList<UserList> getUserListsOwnerships(long listOwnerId,int count,long cursor) throws TwitterException {
  return factory.createPagableUserListList(get(conf.getRestBaseURL() + "lists/ownerships.json",new HttpParameter("user_id",listOwnerId),new HttpParameter("count",count),new HttpParameter("cursor",cursor)));
}
