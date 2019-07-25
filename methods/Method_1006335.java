private void emit(short val){
  if (cardinality == content.length) {
    increaseCapacity(true);
  }
  content[cardinality++]=val;
}
