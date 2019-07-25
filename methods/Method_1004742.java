public static BulkResponse partial(int httpStatus,long spent,int totalWrites,int docsSent,int docsSkipped,int docsAborted,List<BulkError> errors){
  return new BulkResponse(BulkStatus.PARTIAL,httpStatus,spent,totalWrites,docsSent,docsSkipped,docsAborted,errors);
}
