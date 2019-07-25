@Override public void write(Buffer source,long byteCount) throws IOException {
  if (failed)   return;
  try {
    super.write(source,byteCount);
  }
 catch (  Exception e) {
    failed=true;
    onException(e);
  }
}
