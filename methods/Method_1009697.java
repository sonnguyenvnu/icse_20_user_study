private void open() throws IOException {
  Options options=new Options();
  options.createIfMissing(!useExisting);
  if (writeBufferSize != null) {
    options.writeBufferSize(writeBufferSize);
  }
  db=factory.open(databaseDir,options);
}
