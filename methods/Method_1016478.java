private void grow(int minSize){
  int newsize=12 * Math.max(this.buffer.length,minSize) / 10;
  char[] tmp=new char[newsize];
  System.arraycopy(this.buffer,this.offset,tmp,0,this.length);
  this.buffer=tmp;
  this.offset=0;
}
