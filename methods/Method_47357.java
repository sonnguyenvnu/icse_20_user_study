private static int getDefaultCacheSize(){
  int memory=(int)(Runtime.getRuntime().maxMemory() / 1024);
  return memory / 8;
}
