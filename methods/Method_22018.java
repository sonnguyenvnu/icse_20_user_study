@Override public void start(){
  addDataListener(new JobCrashedJobListener());
  addDataListener(new FailoverSettingsChangedJobListener());
}
