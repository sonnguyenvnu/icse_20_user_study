static String guessBetterFormat(ImportingJob job,ObjectNode retrievalRecord,String bestFormat){
  ArrayNode fileRecords=JSONUtilities.getArray(retrievalRecord,"files");
  return fileRecords != null ? guessBetterFormat(job,fileRecords,bestFormat) : bestFormat;
}
