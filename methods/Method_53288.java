@Override public DirectMessage showDirectMessage(long id) throws TwitterException {
  return factory.createDirectMessage(get(conf.getRestBaseURL() + "direct_messages/events/show.json?id=" + id));
}
