@Override public void _XXXXX_(PartitionedEvent partitionedEvent){
  outputCollector.ack(partitionedEvent.getAnchor());
}