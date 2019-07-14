private void clearPrefix() throws IllegalStateException {
  if (iPrefix != null) {
    throw new IllegalStateException("Prefix not followed by field");
  }
  iPrefix=null;
}
