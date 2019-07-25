public ByteArrayDataInputStream position(int pos){
  this.pos=checkBounds(pos);
  return this;
}
