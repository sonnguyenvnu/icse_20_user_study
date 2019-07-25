private void grow(int required){
  int current=this.buf.length;
  int newSize=current << 1;
  if (newSize - required < 0) {
    newSize=required;
  }
  if (newSize - 2147483639 > 0) {
    newSize=hugeCapacity(required);
  }
  byte[] newBuf=new byte[newSize];
  System.arraycopy(buf,0,newBuf,0,count);
  this.buf=newBuf;
}
