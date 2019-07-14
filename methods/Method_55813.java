@NativeType("CUresult") public static int cuParamSetf(@NativeType("CUfunction") long hfunc,int offset,float value){
  long __functionAddress=Functions.ParamSetf;
  if (CHECKS) {
    check(hfunc);
  }
  return callPI(hfunc,offset,value,__functionAddress);
}
