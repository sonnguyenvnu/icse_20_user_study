boolean isLocalInstancePath(final String path){
  return path.equals(jobNodePath.getFullPath(String.format(INSTANCES,JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId())));
}
