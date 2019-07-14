@Override public UserList destroyUserListSubscription(String ownerScreenName,String slug) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/subscribers/destroy.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug)}));
}
