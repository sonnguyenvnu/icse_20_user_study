/** 
 * Initialize the view.
 */
protected void init(HoodieTableMetaClient metaClient,HoodieTimeline visibleActiveTimeline){
  this.metaClient=metaClient;
  this.visibleActiveTimeline=visibleActiveTimeline;
  resetPendingCompactionOperations(CompactionUtils.getAllPendingCompactionOperations(metaClient).values().stream().map(e -> Pair.of(e.getKey(),CompactionOperation.convertFromAvroRecordInstance(e.getValue()))));
}
