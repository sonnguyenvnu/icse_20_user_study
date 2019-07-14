public static Reporter build(String digestLog,String digestRollingPolicy,String digestLogReserveConfig,SpanEncoder<SofaTracerSpan> spanEncoder,SofaTracerStatisticReporter statReporter){
  Reporter reporter=null;
  if (StringUtils.equals(REPORT_TYPE,"MEMORY")) {
    reporter=new MemoryReporterImpl(digestLog,digestRollingPolicy,digestLogReserveConfig,spanEncoder,statReporter);
  }
 else {
    reporter=new DiskReporterImpl(digestLog,digestRollingPolicy,digestLogReserveConfig,spanEncoder,statReporter);
  }
  return reporter;
}
