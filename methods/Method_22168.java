@Override public Collection<JobBriefInfo> getAllJobsBriefInfo(){
  List<String> jobNames=regCenter.getChildrenKeys("/");
  List<JobBriefInfo> result=new ArrayList<>(jobNames.size());
  for (  String each : jobNames) {
    JobBriefInfo jobBriefInfo=getJobBriefInfo(each);
    if (null != jobBriefInfo) {
      result.add(jobBriefInfo);
    }
  }
  Collections.sort(result);
  return result;
}
