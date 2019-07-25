@Override public void delete(BlobPath path) throws IOException {
  IOUtils.rm(buildPath(path));
}
