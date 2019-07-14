private JobContext prepareJobContext(Date now){
  JobContext jobContext=new JobContext();
  jobContext.setJobRequestId(JobContextAssistant.generateJobRequestId());
  jobContext.setJobName("sample job name");
  JobParametersBuilder jobParametersBuilder=new JobParametersBuilder();
  jobParametersBuilder.addDate("time-now",now);
  jobContext.setJobParameters(jobParametersBuilder.toJobParameters());
  return jobContext;
}
