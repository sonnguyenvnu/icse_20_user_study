public static int ncuLinkAddFile(long state,int type,long path,int numOptions,long options,long optionValues){
  long __functionAddress=Functions.LinkAddFile;
  if (CHECKS) {
    check(state);
  }
  return callPPPPI(state,type,path,numOptions,options,optionValues,__functionAddress);
}
