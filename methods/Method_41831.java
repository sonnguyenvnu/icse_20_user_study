@Override public boolean checkExists(final JobKey jobKey){
  return jobFacade.containsKey(jobKey);
}
