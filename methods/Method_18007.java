public static DataFlowGraph getInstance(){
  if (sInstance == null) {
    final ChoreographerTimingSource timingSource=new ChoreographerTimingSource();
    sInstance=new DataFlowGraph(timingSource);
    timingSource.setDataFlowGraph(sInstance);
  }
  return sInstance;
}
