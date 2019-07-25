private List<JobInstance> subset(List<JobInstance> jobInstances,int start,int count){
  int startIndex=Math.min(start,jobInstances.size());
  int endIndex=Math.min(start + count,jobInstances.size());
  return jobInstances.subList(startIndex,endIndex);
}
