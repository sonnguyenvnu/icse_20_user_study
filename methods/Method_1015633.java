@Override public void receive(Address sender,ByteBuffer buf){
  Util.bufferToArray(sender,buf,this);
}
