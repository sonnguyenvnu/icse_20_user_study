@Override public Status destroyFavorite(long id) throws TwitterException {
  return factory.createStatus(post(conf.getRestBaseURL() + "favorites/destroy.json?id=" + id));
}
