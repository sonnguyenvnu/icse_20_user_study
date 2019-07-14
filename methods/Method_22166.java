@Override public void removeJobSettings(final String jobName){
  regCenter.remove("/" + jobName);
}
