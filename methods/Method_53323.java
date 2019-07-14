@Override public PagableResponseList<User> getBlocksList(long cursor) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "blocks/list.json?cursor=" + cursor));
}
