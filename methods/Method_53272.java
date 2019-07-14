@Override public Status showStatus(long id) throws TwitterException {
  return factory.createStatus(get(conf.getRestBaseURL() + "statuses/show/" + id + ".json",new HttpParameter[]{INCLUDE_MY_RETWEET}));
}
