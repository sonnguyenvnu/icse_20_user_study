@Override public void destroyDirectMessage(long id) throws TwitterException {
  ensureAuthorizationEnabled();
  http.delete(conf.getRestBaseURL() + "direct_messages/events/destroy.json?id=" + id,null,auth,null);
}
