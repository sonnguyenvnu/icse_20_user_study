public static int ncuCtxAttach(long pctx,int flags){
  long __functionAddress=Functions.CtxAttach;
  return callPI(pctx,flags,__functionAddress);
}
