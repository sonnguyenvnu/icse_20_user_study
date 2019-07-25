private WritePerformanceCollectedTimes merge(List<WritePerformanceCollectedTimes> all){
  final List<Long> runTimes=new ArrayList<>();
  final List<Long> executionTimes=new ArrayList<>();
  int errors=0;
  for (  final WritePerformanceCollectedTimes time : all) {
    runTimes.addAll(time.getRuntimes());
    executionTimes.addAll(time.getExecutionTimes());
    errors+=time.getErrors();
  }
  return new WritePerformanceCollectedTimes(runTimes,executionTimes,errors);
}
