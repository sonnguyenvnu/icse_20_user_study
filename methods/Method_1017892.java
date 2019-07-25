public void clear(String newCollectionId){
  this.collectionId=newCollectionId;
  for (  Map.Entry<String,AggregatedProcessorStatistics> e : processorStats.entrySet()) {
    e.getValue().clear();
  }
}
