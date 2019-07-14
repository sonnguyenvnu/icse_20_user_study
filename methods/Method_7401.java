public void debugCtl(int request,int param){
  ensureNativeInstance();
  nativeDebugCtl(nativeInst,request,param);
}
