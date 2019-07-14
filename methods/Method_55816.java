@NativeType("CUresult") public static int cuLaunch(@NativeType("CUfunction") long f){
  long __functionAddress=Functions.Launch;
  if (CHECKS) {
    check(f);
  }
  return callPI(f,__functionAddress);
}
