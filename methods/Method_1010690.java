@Override public long length(){
  try {
    return Files.size(getRealFile(this));
  }
 catch (  IOException e) {
    LOG.error(e);
    return 0;
  }
}
