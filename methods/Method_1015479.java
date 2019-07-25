public Buffers position(int new_pos){
  this.position=toPositiveUnsignedShort(new_pos);
  nextToCopy(new_pos);
  return this;
}
