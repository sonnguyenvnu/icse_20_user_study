public List<StubMapping> takeSnapshotRecording(RecordSpecBuilder spec){
  return admin.snapshotRecord(spec.build()).getStubMappings();
}
