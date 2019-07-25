@Override public FileChannel position(long newPos){
  this.pos=(int)newPos;
  return this;
}
