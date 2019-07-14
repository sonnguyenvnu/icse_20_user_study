/** 
 * ????job?? ??job ????????? ??  execute
 * @param jobClass
 * @param groupName
 * @param targetObject
 * @return
 */
private static JobDetailFactoryBean createJobDetail(Class<? extends Job> jobClass,String groupName,String targetObject){
  JobDetailFactoryBean factoryBean=new JobDetailFactoryBean();
  factoryBean.setJobClass(jobClass);
  factoryBean.setDurability(true);
  factoryBean.setRequestsRecovery(true);
  factoryBean.setGroup(groupName);
  Map<String,String> map=new HashMap<>();
  map.put("targetMethod","execute");
  map.put("targetObject",targetObject);
  factoryBean.setJobDataAsMap(map);
  return factoryBean;
}
