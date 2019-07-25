@Override public boolean intersects(ArrayContainer value2){
  int c=value2.cardinality;
  for (int k=0; k < c; ++k) {
    if (this.contains(value2.content[k])) {
      return true;
    }
  }
  return false;
}
