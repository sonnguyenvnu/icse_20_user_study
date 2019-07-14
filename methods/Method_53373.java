@Override public User showUserListSubscription(String ownerScreenName,String slug,long userId) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "lists/subscribers/show.json",new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug),new HttpParameter("user_id",userId)}));
}
