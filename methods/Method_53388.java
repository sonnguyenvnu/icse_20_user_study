@Override public UserList destroyUserList(String ownerScreenName,String slug) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/destroy.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug)}));
}
