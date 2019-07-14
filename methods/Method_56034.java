public static int ncuGLCtxCreate(long pCtx,int Flags,int device){
  long __functionAddress=Functions.GLCtxCreate;
  return callPI(pCtx,Flags,device,__functionAddress);
}
