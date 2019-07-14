@Override public User showUserListMembership(String ownerScreenName,String slug,long userId) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "lists/members/show.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug),new HttpParameter("user_id",userId)}));
}
