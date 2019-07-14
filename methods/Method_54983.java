public static void b3SetProfileTimingDuractionInMicroSeconds(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int duration){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetProfileTimingDuractionInMicroSeconds(commandHandle,duration);
}
