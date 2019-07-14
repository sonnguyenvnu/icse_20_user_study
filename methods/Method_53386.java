@Override public UserList destroyUserList(long listId) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/destroy.json",new HttpParameter[]{new HttpParameter("list_id",listId)}));
}
