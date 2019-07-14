public MP4Atom nextChild() throws IOException {
  if (child != null) {
    child.skip();
  }
  int atomLength=data.readInt();
  byte[] typeBytes=new byte[4];
  data.readFully(typeBytes);
  String atomType=new String(typeBytes,ASCII);
  RangeInputStream atomInput;
  if (atomLength == 1) {
    atomInput=new RangeInputStream(input,16,data.readLong() - 16);
  }
 else {
    atomInput=new RangeInputStream(input,8,atomLength - 8);
  }
  return child=new MP4Atom(atomInput,this,atomType);
}
