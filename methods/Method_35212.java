private void ensureNoDuplicateControllers(List<RouterTransaction> backstack){
  for (int i=0; i < backstack.size(); i++) {
    Controller controller=backstack.get(i).controller;
    for (int j=i + 1; j < backstack.size(); j++) {
      if (backstack.get(j).controller == controller) {
        throw new IllegalStateException("Trying to push the same controller to the backstack more than once.");
      }
    }
  }
}
