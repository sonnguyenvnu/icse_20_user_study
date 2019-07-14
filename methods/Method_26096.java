private boolean hasToleratedException(TryTree tree){
  for (  CatchTree catchTree : tree.getCatches()) {
    if (catchTree.getParameter().getName().contentEquals("tolerated")) {
      return true;
    }
  }
  return false;
}
