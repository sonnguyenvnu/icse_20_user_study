static public String guessSeparator(ImportingJob job,List<ObjectNode> fileRecords){
  for (int i=0; i < 5 && i < fileRecords.size(); i++) {
    ObjectNode fileRecord=fileRecords.get(i);
    String encoding=ImportingUtilities.getEncoding(fileRecord);
    String location=JSONUtilities.getString(fileRecord,"location",null);
    if (location != null) {
      File file=new File(job.getRawDataDir(),location);
      Separator separator=guessSeparator(file,encoding,true);
      if (separator != null) {
        return StringEscapeUtils.escapeJava(Character.toString(separator.separator));
      }
    }
  }
  return null;
}
