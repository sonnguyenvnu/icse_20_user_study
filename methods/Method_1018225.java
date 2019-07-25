public ByteCharCounter decrease(char chr,int val){
  int idx=chr - 32;
  if (isValidChar(chr) && buffer[idx] > 0) {
    buffer[idx]--;
  }
  return this;
}
