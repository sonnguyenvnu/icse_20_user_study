@Override public void _XXXXX_(Tuple input){
  GenericMetricEntity metricEntity=null;
  Map event=null;
  try {
    event=StreamConvertHelper.tupleToEvent(input).f1();
    metricEntity=this.mapper.map(event);
    if (batchSize <= 1) {
      GenericServiceAPIResponseEntity<String> response=this.client.create(Collections.singletonList(metricEntity));
      if (!response.isSuccess()) {
        LOG.error("Service side error: {}",response.getException());
        collector.reportError(new IllegalStateException(response.getException()));
      }
    }
 else {
      this.batchSender.send(metricEntity);
    }
  }
 catch (  Exception ex) {
    LOG.error(ex.getMessage(),ex);
    collector.reportError(ex);
  }
 finally {
    if (metricEntity != null && event != null) {
      collector.emit(Arrays.asList(metricEntity.getPrefix(),event));
    }
    collector.ack(input);
  }
}