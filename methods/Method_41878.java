protected T createNewJobStoreInstance(String schedulerName,final boolean useSynchWrite){
  return (T)new DefaultClusteredJobStore(useSynchWrite,toolkit,schedulerName);
}
