static public String getCommonFormatForSelectedFiles(ImportingJob job,ArrayNode fileSelectionIndexes){
  ObjectNode retrievalRecord=job.getRetrievalRecord();
  final Map<String,Integer> formatToCount=new HashMap<String,Integer>();
  List<String> formats=new ArrayList<String>();
  ArrayNode fileRecords=JSONUtilities.getArray(retrievalRecord,"files");
  int count=fileSelectionIndexes.size();
  for (int i=0; i < count; i++) {
    int index=JSONUtilities.getIntElement(fileSelectionIndexes,i,-1);
    if (index >= 0 && index < fileRecords.size()) {
      ObjectNode fileRecord=JSONUtilities.getObjectElement(fileRecords,index);
      String format=JSONUtilities.getString(fileRecord,"format",null);
      if (format != null) {
        if (formatToCount.containsKey(format)) {
          formatToCount.put(format,formatToCount.get(format) + 1);
        }
 else {
          formatToCount.put(format,1);
          formats.add(format);
        }
      }
    }
  }
  Collections.sort(formats,new Comparator<String>(){
    @Override public int compare(    String o1,    String o2){
      return formatToCount.get(o2) - formatToCount.get(o1);
    }
  }
);
  return formats.size() > 0 ? formats.get(0) : null;
}
