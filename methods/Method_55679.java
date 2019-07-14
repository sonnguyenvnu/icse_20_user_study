private static int memLengthNT2(long address,int maxLength){
  if (CHECKS) {
    check(address);
  }
  return BITS64 ? strlen64NT2(address,maxLength) : strlen32NT2(address,maxLength);
}
