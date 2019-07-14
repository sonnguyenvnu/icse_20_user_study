@Override public void visitEnd(){
  if (numElementValuePairsOffset != -1) {
    byte[] data=annotation.data;
    data[numElementValuePairsOffset]=(byte)(numElementValuePairs >>> 8);
    data[numElementValuePairsOffset + 1]=(byte)numElementValuePairs;
  }
}
