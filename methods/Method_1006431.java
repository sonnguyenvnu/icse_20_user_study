private boolean equals(MappeableRunContainer runContainer){
  if (runContainer.nbrruns != this.nbrruns) {
    return false;
  }
  for (int i=0; i < nbrruns; ++i) {
    if (this.getValue(i) != runContainer.getValue(i)) {
      return false;
    }
    if (this.getLength(i) != runContainer.getLength(i)) {
      return false;
    }
  }
  return true;
}
