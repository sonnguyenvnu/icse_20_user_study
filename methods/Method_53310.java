@Override public PagableResponseList<User> getFriendsList(long userId,long cursor,int count,boolean skipStatus,boolean includeUserEntities) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "friends/list.json?user_id=" + userId + "&cursor=" + cursor + "&count=" + count + "&skip_status=" + skipStatus + "&include_user_entities=" + includeUserEntities));
}
