void add(SnapshotStats stats){
  numberOfFiles+=stats.numberOfFiles;
  processedFiles+=stats.processedFiles;
  totalSize+=stats.totalSize;
  processedSize+=stats.processedSize;
  if (startTime == 0) {
    startTime=stats.startTime;
    time=stats.time;
  }
 else {
    long endTime=Math.max(startTime + time,stats.startTime + stats.time);
    startTime=Math.min(startTime,stats.startTime);
    time=endTime - startTime;
  }
}
