@NativeType("CUresult") public static int cuEventElapsedTime(@NativeType("float *") FloatBuffer pMilliseconds,@NativeType("CUevent") long hStart,@NativeType("CUevent") long hEnd){
  if (CHECKS) {
    check(pMilliseconds,1);
  }
  return ncuEventElapsedTime(memAddress(pMilliseconds),hStart,hEnd);
}
