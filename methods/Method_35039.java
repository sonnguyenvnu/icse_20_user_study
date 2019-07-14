public void createParentDirs(@Nonnull File file) throws IOException {
  File parent=file.getCanonicalFile().getParentFile();
  if (parent == null) {
    return;
  }
  parent.mkdirs();
  if (!parent.isDirectory()) {
    throw new IOException("Unable to create parent directories of " + file);
  }
}
