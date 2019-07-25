@Override public FileChannel open(String mode) throws IOException {
  return new FileNioMapped(name.substring(getScheme().length() + 1),mode);
}
