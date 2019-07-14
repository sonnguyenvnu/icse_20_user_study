public boolean contains(StaticBuffer buffer){
  return sliceStart.compareTo(buffer) <= 0 && sliceEnd.compareTo(buffer) > 0;
}
