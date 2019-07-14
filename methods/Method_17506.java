private int queueIndexFor(Node node){
  for (int i=threshold.length - 1; i >= 0; i--) {
    if (node.reference >= threshold[i]) {
      return i;
    }
  }
  throw new IllegalStateException();
}
