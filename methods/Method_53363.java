@Override public UserList destroyUserListMember(String ownerScreenName,String slug,long userId) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/destroy.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug),new HttpParameter("user_id",userId)}));
}
