public static int ncuParamSetv(long hfunc,int offset,long ptr,int numbytes){
  long __functionAddress=Functions.ParamSetv;
  if (CHECKS) {
    check(hfunc);
  }
  return callPPI(hfunc,offset,ptr,numbytes,__functionAddress);
}
