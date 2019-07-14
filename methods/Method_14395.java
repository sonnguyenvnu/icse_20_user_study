static public void disposeJob(long id){
  ImportingJob job=getJob(id);
  if (job != null) {
    job.dispose();
    jobs.remove(id);
  }
}
