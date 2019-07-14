@Override public UserList destroyUserListMember(long listId,String screenName) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/destroy.json",new HttpParameter[]{new HttpParameter("list_id",listId),new HttpParameter("screen_name",screenName)}));
}
