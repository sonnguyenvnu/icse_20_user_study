@Override public PagableResponseList<User> getMutesList(long cursor) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "mutes/users/list.json?cursor=" + cursor));
}
