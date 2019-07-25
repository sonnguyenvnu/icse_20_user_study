protected static String enable(int type,boolean flag){
  ThreadMXBean bean=ManagementFactory.getThreadMXBean();
  boolean supported=false;
  if (type == 1) {
    supported=bean.isThreadCpuTimeSupported();
    if (supported)     bean.setThreadCpuTimeEnabled(flag);
  }
 else   if (type == 2) {
    supported=bean.isThreadContentionMonitoringSupported();
    if (supported)     bean.setThreadContentionMonitoringEnabled(flag);
  }
  String tmp=type == 1 ? "CPU" : "contention";
  return String.format("%s monitoring supported: %b, %s monitoring enabled: %b",tmp,supported,tmp,supported && flag);
}
