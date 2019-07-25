@Override public FileChannel open(String mode) throws IOException {
  return new FileAsync(name.substring(getScheme().length() + 1),mode);
}
