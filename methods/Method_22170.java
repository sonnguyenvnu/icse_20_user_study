@Override public Collection<JobBriefInfo> getJobsBriefInfo(final String ip){
  List<String> jobNames=regCenter.getChildrenKeys("/");
  List<JobBriefInfo> result=new ArrayList<>(jobNames.size());
  for (  String each : jobNames) {
    JobBriefInfo jobBriefInfo=getJobBriefInfoByJobNameAndIp(each,ip);
    if (null != jobBriefInfo) {
      result.add(jobBriefInfo);
    }
  }
  Collections.sort(result);
  return result;
}
