static final public byte[] parseByte(boolean what[]){
  byte outgoing[]=new byte[what.length];
  for (int i=0; i < what.length; i++) {
    outgoing[i]=what[i] ? (byte)1 : 0;
  }
  return outgoing;
}
