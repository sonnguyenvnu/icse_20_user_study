@Override public void process(Settings settings,HttpServletRequest request,HttpServletResponse response,ProcessInfo processInfo){
  boolean disableMySql=settings.getAsBoolean(ServiceFramwork.mode + ".datasources.mysql.disable",false);
  systemLog(processInfo.startTime,request,settings,processInfo);
  endORM(disableMySql);
  closeTx(settings,processInfo);
  API api=ServiceFramwork.injector.getInstance(API.class);
  api.statusIncrement(processInfo.method,processInfo.status);
  api.averageTimeIncrement(processInfo.method,System.currentTimeMillis() - processInfo.startTime);
}
