static final public byte[] parseByte(float what[]){
  byte outgoing[]=new byte[what.length];
  for (int i=0; i < what.length; i++) {
    outgoing[i]=(byte)what[i];
  }
  return outgoing;
}
