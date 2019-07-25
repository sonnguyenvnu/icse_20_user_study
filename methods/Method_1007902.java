public boolean contains(Marker other){
  if (other == null) {
    throw new IllegalArgumentException("Other cannot be null");
  }
  if (this.equals(other)) {
    return true;
  }
  if (hasReferences()) {
    for (    Marker ref : referenceList) {
      if (ref.contains(other)) {
        return true;
      }
    }
  }
  return false;
}
