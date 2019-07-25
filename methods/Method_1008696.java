protected final boolean contains(long value){
  return Math.floorMod(value,max) == id;
}
