public void configure(JobConf job){
  setConf(job);
  try {
    fs=FileSystem.get(job);
  }
 catch (  Exception e) {
    throw new RuntimeException("Cannot create file system.",e);
  }
}
