@Override public FileRefreshableDataSource getObject() throws Exception {
  return new FileRefreshableDataSource(new File(file),converter,recommendRefreshMs,bufSize,Charset.forName(charset));
}
