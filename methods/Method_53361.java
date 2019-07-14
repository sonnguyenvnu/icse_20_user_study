@Override public UserList destroyUserListMember(long ownerId,String slug,long userId) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/destroy.json",new HttpParameter[]{new HttpParameter("owner_id",ownerId),new HttpParameter("slug",slug),new HttpParameter("user_id",userId)}));
}
