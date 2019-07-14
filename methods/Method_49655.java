private UpdateRequest newUpdateRequest(){
  final UpdateRequest req=new UpdateRequest();
  if (waitSearcher) {
    req.setAction(UpdateRequest.ACTION.COMMIT,true,true);
  }
  return req;
}
