@Override public UserList destroyUserListMembers(String ownerScreenName,String slug,String[] screenNames) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/members/destroy_all.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug),new HttpParameter("screen_name",StringUtil.join(screenNames))}));
}
