public boolean sendFloat(int index,float val){
  if (initialized) {
    try {
      byte[] buf=new byte[12];
      ByteBuffer bb=ByteBuffer.wrap(buf);
      bb.putInt(0,VAR_FLOAT);
      bb.putInt(4,index);
      bb.putFloat(8,val);
      socket.send(new DatagramPacket(buf,buf.length,address,sketchPort));
      return true;
    }
 catch (    Exception e) {
    }
  }
  return false;
}
