private void emit(short val){
  if (cardinality == content.limit()) {
    increaseCapacity(true);
  }
  content.put(cardinality++,val);
}
