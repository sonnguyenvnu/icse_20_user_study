@Override public SnapshotRecordResult snapshotRecord(RecordSpec spec){
  String body=postJsonAssertOkAndReturnBody(urlFor(SnapshotTask.class),Json.write(spec));
  return Json.read(body,SnapshotRecordResult.class);
}
