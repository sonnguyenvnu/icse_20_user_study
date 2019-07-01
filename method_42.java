public boolean _XXXXX_(String yarnAppId,String jobId,JobExecutionAPIEntity entity){
  return this.runningJobManager.update(yarnAppId,jobId,entity.getTags(),entity.getAppInfo());
}