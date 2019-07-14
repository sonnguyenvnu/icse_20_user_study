public static int ncuLaunchHostFunc(long hStream,long fn,long userData){
  long __functionAddress=Functions.LaunchHostFunc;
  if (CHECKS) {
    check(userData);
  }
  return callPPPI(hStream,fn,userData,__functionAddress);
}
