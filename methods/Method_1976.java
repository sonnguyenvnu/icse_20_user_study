private void updateStats(){
  final Runtime runtime=Runtime.getRuntime();
  final long heapMemory=runtime.totalMemory() - runtime.freeMemory();
  final StringBuilder sb=new StringBuilder(DEFAULT_MESSAGE_SIZE);
  sb.append("Heap: ");
  appendSize(sb,heapMemory);
  sb.append(" Java ");
  appendSize(sb,Debug.getNativeHeapSize());
  sb.append(" native\n");
  appendTime(sb,"Avg wait time: ",mPerfListener.getAverageWaitTime(),"\n");
  appendNumber(sb,"Requests: ",mPerfListener.getOutstandingRequests()," outsdng ");
  appendNumber(sb,"",mPerfListener.getCancelledRequests()," cncld\n");
  final String message=sb.toString();
  mStatsDisplay.setText(message);
  FLog.i(TAG,message);
}
