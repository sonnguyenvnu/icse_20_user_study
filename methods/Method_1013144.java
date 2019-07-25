public void terminate(){
  Job.getJobManager().cancel(TLCJob.AllJobsMatcher);
}
