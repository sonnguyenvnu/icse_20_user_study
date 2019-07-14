protected Reporter generateReporter(SofaTracerStatisticReporter statReporter,RpcTracerLogEnum digestRpcTracerLogEnum,SpanEncoder<SofaTracerSpan> spanEncoder){
  String digestLog=digestRpcTracerLogEnum.getDefaultLogName();
  String digestRollingPolicy=SofaTracerConfiguration.getRollingPolicy(digestRpcTracerLogEnum.getRollingKey());
  String digestLogReserveConfig=SofaTracerConfiguration.getLogReserveConfig(digestRpcTracerLogEnum.getLogReverseKey());
  Reporter reporter=ReporterFactory.build(digestLog,digestRollingPolicy,digestLogReserveConfig,spanEncoder,statReporter);
  return reporter;
}
