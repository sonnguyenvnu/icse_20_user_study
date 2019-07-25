public final byte[] copy(){
  byte[] b=new byte[size];
  System.arraycopy(buffer,0,b,0,size);
  return b;
}
