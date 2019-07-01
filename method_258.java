private String _XXXXX_(String name){
  String completeName=scope.isEmpty() ? name : Joiner.on('_').join(scope,name);
  return Collector.sanitizeMetricName(completeName);
}