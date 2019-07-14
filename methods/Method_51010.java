private void requireLinesCorrectlyOrdered(){
  if (beginLine > endLine) {
    throw new IllegalArgumentException("endLine must be equal or greater than beginLine");
  }
}
