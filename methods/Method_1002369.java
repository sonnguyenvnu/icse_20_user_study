private void update(String serviceName,List<Map<String,Object>> backupRequestsConfigs){
  Map<String,BackupRequestsStrategyFromConfig> strategiesForOperation=getOrCreateStrategiesForOperation(serviceName);
  Set<String> operationsInNewConfig=backupRequestsConfigs.stream().map(config -> updateStrategy(serviceName,config,strategiesForOperation)).collect(Collectors.toSet());
  Set<Map.Entry<String,BackupRequestsStrategyFromConfig>> toRemove=strategiesForOperation.entrySet().stream().filter(entry -> !operationsInNewConfig.contains(entry.getKey())).collect(Collectors.toSet());
  toRemove.forEach(entry -> entry.getValue().getStrategy().ifPresent(strategy -> {
    String operation=entry.getKey();
    strategiesForOperation.remove(operation);
    _statsConsumer.ifPresent(consumer -> consumer.removeStatsProvider(serviceName,operation,strategy));
    FinalSweepLatencyNotification fsln=new FinalSweepLatencyNotification(serviceName,operation,strategy);
    _finalSweepLatencyNotification.put(fsln,fsln);
  }
));
}
