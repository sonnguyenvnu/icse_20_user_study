/** 
 * ?????????.
 * @param jobName ????
 * @param jobScheduleController ???????
 * @param regCenter ????
 */
public void registerJob(final String jobName,final JobScheduleController jobScheduleController,final CoordinatorRegistryCenter regCenter){
  schedulerMap.put(jobName,jobScheduleController);
  regCenterMap.put(jobName,regCenter);
  regCenter.addCacheData("/" + jobName);
}
