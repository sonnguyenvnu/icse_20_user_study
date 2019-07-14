@Override public SnapshotRecordResult snapshotRecord(){
  String body=postJsonAssertOkAndReturnBody(urlFor(SnapshotTask.class),"");
  return Json.read(body,SnapshotRecordResult.class);
}
