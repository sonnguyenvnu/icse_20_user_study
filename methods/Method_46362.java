protected SofaTracerStatisticReporter generateClientStatReporter(RpcTracerLogEnum statRpcTracerLogEnum){
  String statLog=statRpcTracerLogEnum.getDefaultLogName();
  String statRollingPolicy=SofaTracerConfiguration.getRollingPolicy(statRpcTracerLogEnum.getRollingKey());
  String statLogReserveConfig=SofaTracerConfiguration.getLogReserveConfig(statRpcTracerLogEnum.getLogReverseKey());
  return new RpcClientStatJsonReporter(statLog,statRollingPolicy,statLogReserveConfig);
}
