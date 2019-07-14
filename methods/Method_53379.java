@Override public UserList createUserListMembers(long listId,String... screenNames) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/create_all.json",new HttpParameter[]{new HttpParameter("list_id",listId),new HttpParameter("screen_name",StringUtil.join(screenNames))}));
}
