@Override public FileChannel open(String mode) throws IOException {
  return new FileRetryOnInterrupt(name.substring(getScheme().length() + 1),mode);
}
