@Override public void exception(Throwable t) throws Exception {
  con.disconnect(Util.exception(t));
}
