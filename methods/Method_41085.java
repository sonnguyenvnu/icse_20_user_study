public void jobsResumed(String jobGroup){
  Map<String,String> map=new HashMap<String,String>();
  map.put("jobName",null);
  map.put("jobGroup",jobGroup);
  sendNotification(JOBS_RESUMED,map);
}
