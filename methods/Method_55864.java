public static int ncuPointerGetAttribute(long data,int attribute,long ptr){
  long __functionAddress=Functions.PointerGetAttribute;
  if (CHECKS) {
    check(ptr);
  }
  return callPPI(data,attribute,ptr,__functionAddress);
}
