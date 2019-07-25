public static void jstack(OutputStream stream) throws Exception {
  ThreadMXBean threadMxBean=ManagementFactory.getThreadMXBean();
  for (  ThreadInfo threadInfo : threadMxBean.dumpAllThreads(true,true)) {
    stream.write(getThreadDumpString(threadInfo).getBytes());
  }
}
