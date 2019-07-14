@Override public Status createFavorite(long id) throws TwitterException {
  return factory.createStatus(post(conf.getRestBaseURL() + "favorites/create.json?id=" + id));
}
