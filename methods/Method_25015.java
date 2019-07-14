@Override public void clear(){
  for (  ITempFile file : this.tempFiles) {
    try {
      file.delete();
    }
 catch (    Exception ignored) {
      NanoHTTPD.LOG.log(Level.WARNING,"could not delete file ",ignored);
    }
  }
  this.tempFiles.clear();
}
