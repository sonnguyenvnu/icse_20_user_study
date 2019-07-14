static String guessBetterFormat(ImportingJob job,String bestFormat){
  ObjectNode retrievalRecord=job.getRetrievalRecord();
  return retrievalRecord != null ? guessBetterFormat(job,retrievalRecord,bestFormat) : bestFormat;
}
