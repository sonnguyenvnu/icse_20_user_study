@Override public PagableResponseList<User> getFollowersList(long userId,long cursor,int count) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "followers/list.json?user_id=" + userId + "&cursor=" + cursor + "&count=" + count));
}
