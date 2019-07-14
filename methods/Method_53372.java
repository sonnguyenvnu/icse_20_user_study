@Override public User showUserListSubscription(long ownerId,String slug,long userId) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "lists/subscribers/show.json?owner_id=" + ownerId + "&slug=" + slug + "&user_id=" + userId));
}
