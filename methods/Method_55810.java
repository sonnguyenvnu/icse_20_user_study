public static int ncuFuncGetAttribute(long pi,int attrib,long hfunc){
  long __functionAddress=Functions.FuncGetAttribute;
  if (CHECKS) {
    check(hfunc);
  }
  return callPPI(pi,attrib,hfunc,__functionAddress);
}
