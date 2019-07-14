@Override public UserList destroyUserListMember(long listId,long userId) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/destroy.json",new HttpParameter[]{new HttpParameter("list_id",listId),new HttpParameter("user_id",userId)}));
}
