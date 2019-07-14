public MP4Atom nextChildUpTo(String expectedTypeExpression) throws IOException {
  while (true) {
    MP4Atom atom=nextChild();
    if (atom.getType().matches(expectedTypeExpression)) {
      return atom;
    }
  }
}
