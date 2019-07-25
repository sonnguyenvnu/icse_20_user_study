private boolean handler(JobMTask onlineTask){
  onlineTask.setOutParam(null);
  List<String> executorInstance=CommonService.getExecutorInstance(onlineTask);
  String routeInstance=RouteStrategyHandler.failHandle(onlineTask,executorInstance);
  if (StringHelper.isEmpty(routeInstance)) {
    return true;
  }
  onlineTask.setCurrentHandler(routeInstance);
  new TaskHttpClient().async(onlineTask);
  return false;
}
