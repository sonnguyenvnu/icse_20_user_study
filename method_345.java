private String _XXXXX_(List<String> optSettings){
  StringBuilder sb=new StringBuilder();
  long numReduces=context.getNumReduces();
  if (numReduces > 0) {
    long avgReduceTime=context.getAvgReduceTimeInSec();
    long avgShuffleTime=context.getAvgShuffleTimeInSec();
    long avgShuffleBytes=context.getJob().getReduceCounters().getCounterValue(JobCounters.CounterName.REDUCE_SHUFFLE_BYTES) / numReduces;
    long avgReduceOutput=context.getJob().getReduceCounters().getCounterValue(JobCounters.CounterName.HDFS_BYTES_WRITTEN) / numReduces;
    long avgReduceTotalTime=avgShuffleTime + avgReduceTime;
    long suggestReduces=0;
    StringBuilder tmpsb=new StringBuilder();
    String avgShuffleDisplaySize=bytesToHumanReadable(avgShuffleBytes);
    if (avgShuffleBytes < 256 * FileUtils.ONE_MB && avgReduceTotalTime < 300 && avgReduceOutput < 256 * FileUtils.ONE_MB && numReduces > 1) {
      tmpsb.append("average reduce input bytes is: ").append(avgShuffleDisplaySize).append(", ");
      suggestReduces=getReduceNum(avgShuffleBytes,avgReduceOutput,avgReduceTime);
    }
 else     if (avgShuffleBytes > 10 * FileUtils.ONE_GB && avgReduceTotalTime > 1800) {
      tmpsb.append("average reduce input bytes is: ").append(avgShuffleDisplaySize).append(", ");
      suggestReduces=getReduceNum(avgShuffleBytes,avgReduceOutput,avgReduceTime);
    }
    if (avgReduceTotalTime < 60 && numReduces > 1) {
      tmpsb.append("average reduce time is only ").append(avgReduceTotalTime).append(" seconds, ");
      if (suggestReduces == 0) {
        suggestReduces=getReduceNum(avgShuffleBytes,avgReduceOutput,avgReduceTime);
      }
    }
 else     if (avgReduceTotalTime > 3600 && avgReduceTime > 1800) {
      tmpsb.append("average reduce time is ").append(avgReduceTotalTime).append(" seconds, ");
      if (suggestReduces == 0) {
        suggestReduces=getReduceNum(avgShuffleBytes,avgReduceOutput,avgReduceTime);
      }
    }
    String avgReduceOutputDisplaySize=bytesToHumanReadable(avgReduceOutput);
    if (avgReduceOutput < 10 * FileUtils.ONE_MB && avgReduceTime < 300 && avgShuffleBytes < 2 * FileUtils.ONE_GB && numReduces > 1) {
      tmpsb.append(" average reduce output is only ").append(avgReduceOutputDisplaySize).append(", ");
      if (suggestReduces == 0) {
        suggestReduces=getReduceNum(avgShuffleBytes,avgReduceOutput,avgReduceTime);
      }
    }
 else     if (avgReduceOutput > 10 * FileUtils.ONE_GB && avgReduceTime > 1800) {
      tmpsb.append(" average reduce output is ").append(avgReduceOutputDisplaySize).append(", ");
      if (suggestReduces == 0) {
        suggestReduces=getReduceNum(avgShuffleBytes,avgReduceOutput,avgReduceTime);
      }
    }
    if (suggestReduces > 0) {
      sb.append("Best practice: ").append(tmpsb.toString()).append("please consider ");
      if (suggestReduces > numReduces) {
        sb.append("increasing the ");
      }
 else {
        sb.append("decreasing the ");
      }
      String setting=String.format("-D%s=%s",NUM_REDUCES,suggestReduces);
      sb.append("reducer number. You could try ").append(setting).append("\n");
      optSettings.add(setting);
    }
  }
  return sb.toString();
}