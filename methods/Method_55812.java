@NativeType("CUresult") public static int cuParamSeti(@NativeType("CUfunction") long hfunc,int offset,@NativeType("unsigned int") int value){
  long __functionAddress=Functions.ParamSeti;
  if (CHECKS) {
    check(hfunc);
  }
  return callPI(hfunc,offset,value,__functionAddress);
}
