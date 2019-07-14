public default String digestString(final File file) throws IOException {
  return StringUtil.toHexString(digest(file));
}
