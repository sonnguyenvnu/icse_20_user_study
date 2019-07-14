public void jobResumed(JobKey jobKey){
  Map<String,String> map=new HashMap<String,String>();
  map.put("jobName",jobKey.getName());
  map.put("jobGroup",jobKey.getGroup());
  sendNotification(JOBS_RESUMED,map);
}
