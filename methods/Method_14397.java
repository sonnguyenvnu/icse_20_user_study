static public void loadDataAndPrepareJob(HttpServletRequest request,HttpServletResponse response,Properties parameters,final ImportingJob job,ObjectNode config) throws IOException, ServletException {
  ObjectNode retrievalRecord=ParsingUtilities.mapper.createObjectNode();
  JSONUtilities.safePut(config,"retrievalRecord",retrievalRecord);
  JSONUtilities.safePut(config,"state","loading-raw-data");
  final ObjectNode progress=ParsingUtilities.mapper.createObjectNode();
  JSONUtilities.safePut(config,"progress",progress);
  try {
    ImportingUtilities.retrieveContentFromPostRequest(request,parameters,job.getRawDataDir(),retrievalRecord,new Progress(){
      @Override public void setProgress(      String message,      int percent){
        if (message != null) {
          JSONUtilities.safePut(progress,"message",message);
        }
        JSONUtilities.safePut(progress,"percent",percent);
      }
      @Override public boolean isCanceled(){
        return job.canceled;
      }
    }
);
  }
 catch (  Exception e) {
    JSONUtilities.safePut(config,"state","error");
    JSONUtilities.safePut(config,"error","Error uploading data");
    JSONUtilities.safePut(config,"errorDetails",e.getLocalizedMessage());
    return;
  }
  ArrayNode fileSelectionIndexes=ParsingUtilities.mapper.createArrayNode();
  JSONUtilities.safePut(config,"fileSelection",fileSelectionIndexes);
  String bestFormat=ImportingUtilities.autoSelectFiles(job,retrievalRecord,fileSelectionIndexes);
  bestFormat=ImportingUtilities.guessBetterFormat(job,bestFormat);
  ArrayNode rankedFormats=ParsingUtilities.mapper.createArrayNode();
  ImportingUtilities.rankFormats(job,bestFormat,rankedFormats);
  JSONUtilities.safePut(config,"rankedFormats",rankedFormats);
  JSONUtilities.safePut(config,"state","ready");
  JSONUtilities.safePut(config,"hasData",true);
  config.remove("progress");
}
