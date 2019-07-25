@Override public void create() throws IOException {
  throwClosed();
  if (!file.createNewFile()) {
    throw new IOException("File already exists  " + file.getAbsolutePath());
  }
}
