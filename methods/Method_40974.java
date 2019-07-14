@Override public void unregister(QuartzScheduler quartzScheduler){
  this.cacheManagerSamplerRepo.remove(quartzScheduler.getSchedulerName());
}
