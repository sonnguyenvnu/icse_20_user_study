/** 
 * ????????????.
 * @return ??????????
 */
public List<JobInstance> getAvailableJobInstances(){
  List<JobInstance> result=new LinkedList<>();
  for (  String each : jobNodeStorage.getJobNodeChildrenKeys(InstanceNode.ROOT)) {
    JobInstance jobInstance=new JobInstance(each);
    if (serverService.isEnableServer(jobInstance.getIp())) {
      result.add(new JobInstance(each));
    }
  }
  return result;
}
