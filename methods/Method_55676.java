private static int memLengthNT1(long address,int maxLength){
  if (CHECKS) {
    check(address);
  }
  return BITS64 ? strlen64NT1(address,maxLength) : strlen32NT1(address,maxLength);
}
