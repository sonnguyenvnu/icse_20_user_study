protected static void fillByteBuffer(ByteBuffer buf,int i0,int i1,byte val){
  int n=i1 - i0;
  byte[] temp=new byte[n];
  Arrays.fill(temp,0,n,val);
  buf.position(i0);
  buf.put(temp,0,n);
  buf.rewind();
}
