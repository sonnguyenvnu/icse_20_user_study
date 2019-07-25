@Override public void flush() throws IOException {
  if (!transactionActive() && forceSync) {
    channel.force(false);
  }
}
