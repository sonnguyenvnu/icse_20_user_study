/** 
 * Stops TLC
 */
public void stop(){
  if (getModel().isRunning()) {
    Job[] runningSpecJobs=Job.getJobManager().find(getModel().getLaunchConfiguration());
    for (int i=0; i < runningSpecJobs.length; i++) {
      runningSpecJobs[i].cancel();
    }
  }
 else   if (getModel().isRunningRemotely()) {
    final Job[] remoteJobs=Job.getJobManager().find(getModel());
    for (    Job remoteJob : remoteJobs) {
      remoteJob.cancel();
    }
  }
}
