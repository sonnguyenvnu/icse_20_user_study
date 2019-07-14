public static int ncuEventElapsedTime(long pMilliseconds,long hStart,long hEnd){
  long __functionAddress=Functions.EventElapsedTime;
  if (CHECKS) {
    check(hStart);
    check(hEnd);
  }
  return callPPPI(pMilliseconds,hStart,hEnd,__functionAddress);
}
