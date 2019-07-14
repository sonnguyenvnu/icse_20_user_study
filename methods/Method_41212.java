/** 
 * Same as {@link DirectSchedulerFactory#createRemoteScheduler(String rmiHost,int rmiPort)}, with the addition of specifying the scheduler name, instance ID, and rmi bind name. This scheduler can only be retrieved via {@link DirectSchedulerFactory#getScheduler(String)}
 * @param schedulerName The name for the scheduler.
 * @param schedulerInstanceId The instance ID for the scheduler.
 * @param rmiBindName The name of the remote scheduler in the RMI repository.  If null defaults to the generated unique identifier.
 * @param rmiHost The hostname for remote scheduler
 * @param rmiPort Port for the remote scheduler. The default RMI port is 1099.
 * @throws SchedulerException if the remote scheduler could not be reached.
 */
public void createRemoteScheduler(String schedulerName,String schedulerInstanceId,String rmiBindName,String rmiHost,int rmiPort) throws SchedulerException {
  String uid=(rmiBindName != null) ? rmiBindName : QuartzSchedulerResources.getUniqueIdentifier(schedulerName,schedulerInstanceId);
  RemoteScheduler remoteScheduler=new RemoteScheduler(uid,rmiHost,rmiPort);
  SchedulerRepository schedRep=SchedulerRepository.getInstance();
  schedRep.bind(remoteScheduler);
  initialized=true;
}
