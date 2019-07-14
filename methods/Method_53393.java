@Override public UserList showUserList(long ownerId,String slug) throws TwitterException {
  return factory.createAUserList(get(conf.getRestBaseURL() + "lists/show.json?owner_id=" + ownerId + "&slug=" + slug));
}
