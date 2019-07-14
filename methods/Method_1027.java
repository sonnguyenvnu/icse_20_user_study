private static synchronized void ensure(){
  if (!sInitialized) {
    sInitialized=true;
    SoLoader.loadLibrary("gifimage");
  }
}
