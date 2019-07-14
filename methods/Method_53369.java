@Override public UserList createUserListSubscription(long ownerId,String slug) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/subscribers/create.json",new HttpParameter[]{new HttpParameter("owner_id",ownerId),new HttpParameter("slug",slug)}));
}
