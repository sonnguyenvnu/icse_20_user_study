static public ImportingJob createJob(){
  long id;
synchronized (jobIdLock) {
    ++jobIdCounter;
    if (jobIdCounter < 0) {
      jobIdCounter=1;
    }
    id=jobIdCounter;
  }
  File jobDir=new File(getImportDir(),Long.toString(id));
  ImportingJob job=new ImportingJob(id,jobDir);
  jobs.put(id,job);
  return job;
}
