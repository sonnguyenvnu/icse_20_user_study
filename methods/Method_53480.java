@Override public TwitterStream firehose(final int count){
  ensureAuthorizationEnabled();
  ensureStatusStreamListenerIsSet();
  startHandler(new TwitterStreamConsumer(Mode.status){
    @Override public StatusStream getStream() throws TwitterException {
      return getFirehoseStream(count);
    }
  }
);
  return this;
}
