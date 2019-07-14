@Override public UserList destroyUserListMembers(long listId,long[] userIds) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/destroy_all.json",new HttpParameter[]{new HttpParameter("list_id",listId),new HttpParameter("user_id",StringUtil.join(userIds))}));
}
