@Override public FileChannel open(String mode) throws IOException {
  return new FileNio(name.substring(getScheme().length() + 1),mode);
}
