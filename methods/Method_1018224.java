public ByteCharCounter increase(char chr,int val){
  if (isValidChar(chr)) {
    buffer[chr - 32]+=val;
  }
  return this;
}
