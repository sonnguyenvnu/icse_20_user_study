@Override public User showUserListMembership(long listId,long userId) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "lists/members/show.json?list_id=" + listId + "&user_id=" + userId));
}
