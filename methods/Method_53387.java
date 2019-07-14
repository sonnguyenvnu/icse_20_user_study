@Override public UserList destroyUserList(long ownerId,String slug) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/destroy.json",new HttpParameter[]{new HttpParameter("owner_id",ownerId),new HttpParameter("slug",slug)}));
}
