@Nonnull @Override public BufferedSource read(@Nonnull String path) throws FileNotFoundException {
  return getFile(path).source();
}
