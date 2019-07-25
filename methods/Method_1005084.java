@Override public boolean reseek(final byte[] row) throws IOException {
  return getScanner().reseek(row);
}
