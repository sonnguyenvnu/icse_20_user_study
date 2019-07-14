@Override public UserList createUserListMembers(long ownerId,String slug,String... screenNames) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/create_all.json",new HttpParameter[]{new HttpParameter("owner_id",ownerId),new HttpParameter("slug",slug),new HttpParameter("screen_name",StringUtil.join(screenNames))}));
}
