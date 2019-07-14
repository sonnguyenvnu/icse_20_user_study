public SnapshotRecordResult snapshotRecord(RecordSpec recordSpec){
  return recorder.takeSnapshot(getServeEvents().getServeEvents(),recordSpec);
}
