@Override public void write(final String database,final String retentionPolicy,final Point point){
  if (this.batchEnabled.get()) {
    HttpBatchEntry batchEntry=new HttpBatchEntry(point,database,retentionPolicy);
    this.batchProcessor.put(batchEntry);
  }
 else {
    BatchPoints batchPoints=BatchPoints.database(database).retentionPolicy(retentionPolicy).build();
    batchPoints.point(point);
    this.write(batchPoints);
    this.unBatchedCount.increment();
  }
  this.writeCount.increment();
}
