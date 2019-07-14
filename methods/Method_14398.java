static public void updateJobWithNewFileSelection(ImportingJob job,ArrayNode fileSelectionArray){
  job.setFileSelection(fileSelectionArray);
  String bestFormat=ImportingUtilities.getCommonFormatForSelectedFiles(job,fileSelectionArray);
  bestFormat=ImportingUtilities.guessBetterFormat(job,bestFormat);
  ArrayNode rankedFormats=ParsingUtilities.mapper.createArrayNode();
  ImportingUtilities.rankFormats(job,bestFormat,rankedFormats);
  job.setRankedFormats(rankedFormats);
}
