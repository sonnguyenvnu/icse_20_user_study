@Override public void next(UserStreamListener listener) throws TwitterException {
  handleNextElement(new StreamListener[]{listener},EMPTY);
}
