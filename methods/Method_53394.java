@Override public UserList showUserList(String ownerScreenName,String slug) throws TwitterException {
  return factory.createAUserList(get(conf.getRestBaseURL() + "lists/show.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug)}));
}
