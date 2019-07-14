@Override public PagableResponseList<User> getFriendsList(String screenName,long cursor,int count) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "friends/list.json",new HttpParameter("screen_name",screenName),new HttpParameter("cursor",cursor),new HttpParameter("count",count)));
}
