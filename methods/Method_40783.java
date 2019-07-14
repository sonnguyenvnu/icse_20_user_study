public static String getGCStats(){
  long totalGC=0;
  long gcTime=0;
  for (  GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
    long count=gc.getCollectionCount();
    if (count >= 0) {
      totalGC+=count;
    }
    long time=gc.getCollectionTime();
    if (time >= 0) {
      gcTime+=time;
    }
  }
  StringBuilder sb=new StringBuilder();
  sb.append(banner("memory stats"));
  sb.append("\n- total collections: " + totalGC);
  sb.append("\n- total collection time: " + formatTime(gcTime));
  Runtime runtime=Runtime.getRuntime();
  sb.append("\n- total memory: " + _.printMem(runtime.totalMemory()));
  return sb.toString();
}
