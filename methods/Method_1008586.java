private int similarity(ThreadInfo threadInfo,ThreadInfo threadInfo0){
  StackTraceElement[] s1=threadInfo == null ? EMPTY : threadInfo.getStackTrace();
  StackTraceElement[] s2=threadInfo0 == null ? EMPTY : threadInfo0.getStackTrace();
  int i=s1.length - 1;
  int j=s2.length - 1;
  int rslt=0;
  while (i >= 0 && j >= 0 && s1[i].equals(s2[j])) {
    rslt++;
    i--;
    j--;
  }
  return rslt;
}
