public static int syscall(int number,Object... args){
  final CLibrary lib=CLibrary.INSTANCE;
  try {
    final int ret=lib.syscall(number,args);
    if (ret < 0) {
      throw new IllegalStateException("sched_getcpu() failed; errno=" + Native.getLastError());
    }
    return ret;
  }
 catch (  LastErrorException e) {
    throw new IllegalStateException("sched_getcpu() failed; errno=" + e.getErrorCode(),e);
  }
}
