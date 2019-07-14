@Override public void listen(final JobStatusTraceEvent jobStatusTraceEvent){
  repository.addJobStatusTraceEvent(jobStatusTraceEvent);
}
