@Override public UserList createUserListMembers(String ownerScreenName,String slug,long... userIds) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/create_all.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug),new HttpParameter("user_id",StringUtil.join(userIds))}));
}
