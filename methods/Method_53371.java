@Override public User showUserListSubscription(long listId,long userId) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "lists/subscribers/show.json?list_id=" + listId + "&user_id=" + userId));
}
