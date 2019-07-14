@Override public PagableResponseList<User> getFriendsList(long userId,long cursor,int count) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "friends/list.json?user_id=" + userId + "&cursor=" + cursor + "&count=" + count));
}
