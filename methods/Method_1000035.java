public byte[] sweep(int n){
  if (pc + n > ops.length) {
    stop();
  }
  byte[] data=Arrays.copyOfRange(ops,pc,pc + n);
  pc+=n;
  if (pc >= ops.length) {
    stop();
  }
  return data;
}
