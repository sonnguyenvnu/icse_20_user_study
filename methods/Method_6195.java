public MP4Atom nextChild(String expectedTypeExpression) throws IOException {
  MP4Atom atom=nextChild();
  if (atom.getType().matches(expectedTypeExpression)) {
    return atom;
  }
  throw new IOException("atom type mismatch, expected " + expectedTypeExpression + ", got " + atom.getType());
}
