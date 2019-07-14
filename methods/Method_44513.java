public Date getServerTime() throws IOException {
  return new Date(getKuna().getTimestamp());
}
