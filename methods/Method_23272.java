static final public char[] parseChar(byte what[]){
  char outgoing[]=new char[what.length];
  for (int i=0; i < what.length; i++) {
    outgoing[i]=(char)(what[i] & 0xff);
  }
  return outgoing;
}
