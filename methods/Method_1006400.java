@Override public void clear(){
  if (cardinality != 0) {
    cardinality=0;
    int len=this.bitmap.limit();
    for (int k=0; k < len; ++k) {
      bitmap.put(k,0);
    }
  }
}
