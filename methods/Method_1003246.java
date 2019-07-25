@Override public void force(boolean metaData) throws IOException {
  String m=SysProperties.SYNC_METHOD;
  if ("".equals(m)) {
  }
 else   if ("sync".equals(m)) {
    file.getFD().sync();
  }
 else   if ("force".equals(m)) {
    file.getChannel().force(true);
  }
 else   if ("forceFalse".equals(m)) {
    file.getChannel().force(false);
  }
 else {
    file.getFD().sync();
  }
}
