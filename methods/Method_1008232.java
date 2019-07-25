public boolean retryable(){
  for (  ClusterBlock block : blocks) {
    if (!block.retryable()) {
      return false;
    }
  }
  return true;
}
