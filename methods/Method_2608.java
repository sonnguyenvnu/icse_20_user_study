@Override public boolean load(ByteArray byteArray){
  letter=byteArray.nextChar();
  isAcceptNode=byteArray.nextByte() == 1;
  transitionSetBeginIndex=byteArray.nextInt();
  transitionSetSize=byteArray.nextInt();
  return true;
}
