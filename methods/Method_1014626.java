public static int getpid(){
  final CLibrary lib=CLibrary.INSTANCE;
  try {
    final int ret=lib.getpid();
    if (ret < 0) {
      throw new IllegalStateException("getpid() failed; errno=" + Native.getLastError());
    }
    return ret;
  }
 catch (  LastErrorException e) {
    throw new IllegalStateException("getpid() failed; errno=" + e.getErrorCode(),e);
  }
}
