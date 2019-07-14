@Override protected void configureJob(JobInfo.Builder job){
  job.setRequiredNetworkType(schedulerConfiguration.requiresNetworkType());
  job.setRequiresCharging(schedulerConfiguration.requiresCharging());
  job.setRequiresDeviceIdle(schedulerConfiguration.requiresDeviceIdle());
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    job.setRequiresBatteryNotLow(schedulerConfiguration.requiresBatteryNotLow());
  }
}
