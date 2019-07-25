@Override public void receive(Address sender,ByteBuffer buf){
  byte[] buffer=buf.array();
  int len=Bits.readInt(buffer,buf.arrayOffset());
  String msg=new String(buffer,buf.arrayOffset() + Global.INT_SIZE,len);
  System.out.printf("-- %s\n",msg);
}
