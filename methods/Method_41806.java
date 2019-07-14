public SerializedToolkitStore<JobKey,JobWrapper> getOrCreateJobsMap(){
  String jobsMapName=generateName(JOBS_MAP_PREFIX);
  SerializedToolkitStore<JobKey,JobWrapper> temp=new SerializedToolkitStore<JobKey,JobWrapper>(createStore(jobsMapName));
  jobsMapReference.compareAndSet(null,temp);
  return jobsMapReference.get();
}
