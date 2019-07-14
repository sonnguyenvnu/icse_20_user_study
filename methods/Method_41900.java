public void setJobDataMap(JobDataMap newData,JobFacade jobFacade){
  jobDetail=jobDetail.getJobBuilder().setJobData(newData).build();
  jobFacade.put(jobDetail.getKey(),this);
}
