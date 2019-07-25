@Override public FileChannel position(long newPos){
  this.pos=newPos;
  return this;
}
