@Override public UserList destroyUserListSubscription(long listId) throws TwitterException {
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/subscribers/destroy.json",new HttpParameter[]{new HttpParameter("list_id",listId)}));
}
