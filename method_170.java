private static void _XXXXX_(){
  try {
    NativeUtils.loadLibraryFromJar("/lib/libcpu-affinity.so");
    isSupported=CpuAffinityJni.isAvailable();
  }
 catch (  final Exception|UnsatisfiedLinkError e) {
    log.warn("Unable to load CPU affinity library: {}",e.getMessage(),e);
    isSupported=false;
  }
 finally {
    isInitialized=true;
  }
}