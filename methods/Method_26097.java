private boolean hasExpectedException(TryTree tree){
  for (  CatchTree catchTree : tree.getCatches()) {
    if (catchTree.getParameter().getName().contentEquals("expected")) {
      return true;
    }
  }
  return false;
}
