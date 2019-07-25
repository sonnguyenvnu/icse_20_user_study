public void receive(LogVO logVo){
  SysLog sysLog=logVo.getSysLog();
  MDC.put(KEY_USER,logVo.getUsername());
  sysLog.setCreateBy(logVo.getUsername());
  sysLogService.save(sysLog);
  MDC.remove(KEY_USER);
}
