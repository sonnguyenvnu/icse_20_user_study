private void assertBound(){
  if (epoxyModel == null) {
    throw new IllegalStateException("This holder is not currently bound.");
  }
}
