public boolean contains(ClusterBlockLevel level){
  for (  ClusterBlockLevel testLevel : levels) {
    if (testLevel == level) {
      return true;
    }
  }
  return false;
}
