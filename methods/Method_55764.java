public static int ncuModuleGetGlobal(long dptr,long bytes,long hmod,long name){
  long __functionAddress=Functions.ModuleGetGlobal;
  if (CHECKS) {
    check(hmod);
  }
  return callPPPPI(dptr,bytes,hmod,name,__functionAddress);
}
