public static int ncuLinkCreate(int numOptions,long options,long optionValues,long stateOut){
  long __functionAddress=Functions.LinkCreate;
  return callPPPI(numOptions,options,optionValues,stateOut,__functionAddress);
}
