private List<JobInstance> rotateServerList(final List<JobInstance> shardingUnits,final String jobName){
  int shardingUnitsSize=shardingUnits.size();
  int offset=Math.abs(jobName.hashCode()) % shardingUnitsSize;
  if (0 == offset) {
    return shardingUnits;
  }
  List<JobInstance> result=new ArrayList<>(shardingUnitsSize);
  for (int i=0; i < shardingUnitsSize; i++) {
    int index=(i + offset) % shardingUnitsSize;
    result.add(shardingUnits.get(index));
  }
  return result;
}
