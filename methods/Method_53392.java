@Override public UserList showUserList(long listId) throws TwitterException {
  return factory.createAUserList(get(conf.getRestBaseURL() + "lists/show.json?list_id=" + listId));
}
