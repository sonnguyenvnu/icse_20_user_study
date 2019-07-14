public static int ncuLinkAddData(long state,int type,long data,long size,long name,int numOptions,long options,long optionValues){
  long __functionAddress=Functions.LinkAddData;
  if (CHECKS) {
    check(state);
  }
  return callPPPPPPI(state,type,data,size,name,numOptions,options,optionValues,__functionAddress);
}
