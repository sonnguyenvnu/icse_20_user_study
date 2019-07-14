@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  RecordSpec recordSpec=request.getBody().length == 0 ? RecordSpec.DEFAULTS : Json.read(request.getBodyAsString(),RecordSpec.class);
  SnapshotRecordResult result=admin.snapshotRecord(recordSpec);
  return jsonResponse(result,HTTP_OK);
}
