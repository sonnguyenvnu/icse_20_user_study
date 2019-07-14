public MP4Atom nextChildUpTo(String expectedTypeExpression) throws IOException {
  while (getRemaining() > 0) {
    MP4Atom atom=nextChild();
    if (atom.getType().matches(expectedTypeExpression)) {
      return atom;
    }
  }
  throw new IOException("atom type mismatch, not found: " + expectedTypeExpression);
}
