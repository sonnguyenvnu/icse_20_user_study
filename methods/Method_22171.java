private JobBriefInfo.JobStatus getJobStatusByJobNameAndIp(final String jobName,final String ip){
  JobNodePath jobNodePath=new JobNodePath(jobName);
  String status=regCenter.get(jobNodePath.getServerNodePath(ip));
  if ("DISABLED".equalsIgnoreCase(status)) {
    return JobBriefInfo.JobStatus.DISABLED;
  }
 else {
    return JobBriefInfo.JobStatus.OK;
  }
}
