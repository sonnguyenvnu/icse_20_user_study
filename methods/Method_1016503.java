public void commit(final File target) throws IOException {
  this.propFile=target;
  commit(true);
}
