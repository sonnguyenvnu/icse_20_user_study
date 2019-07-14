/** 
 * Figure out the best (most common) format for the set of files, select all files which match that format, and return the format found.
 * @param job ImportingJob object
 * @param retrievalRecord JSON object containing "files" key with all our files
 * @param fileSelectionIndexes JSON array of selected file indices matching best format
 * @return best (highest frequency) format
 */
static public String autoSelectFiles(ImportingJob job,ObjectNode retrievalRecord,ArrayNode fileSelectionIndexes){
  final Map<String,Integer> formatToCount=new HashMap<String,Integer>();
  List<String> formats=new ArrayList<String>();
  ArrayNode fileRecords=JSONUtilities.getArray(retrievalRecord,"files");
  int count=fileRecords.size();
  for (int i=0; i < count; i++) {
    ObjectNode fileRecord=JSONUtilities.getObjectElement(fileRecords,i);
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
  Collections.sort(formats,new Comparator<String>(){
    @Override public int compare(    String o1,    String o2){
      return formatToCount.get(o2) - formatToCount.get(o1);
    }
  }
);
  String bestFormat=formats.size() > 0 ? formats.get(0) : "text/line-based";
  if (JSONUtilities.getInt(retrievalRecord,"archiveCount",0) == 0) {
    for (int i=0; i < count; i++) {
      JSONUtilities.append(fileSelectionIndexes,i);
    }
  }
 else {
    for (int i=0; i < count; i++) {
      ObjectNode fileRecord=JSONUtilities.getObjectElement(fileRecords,i);
      String format=JSONUtilities.getString(fileRecord,"format",null);
      if (format != null && format.equals(bestFormat)) {
        JSONUtilities.append(fileSelectionIndexes,i);
      }
    }
    if (fileSelectionIndexes.size() == 0 && count > 0) {
      for (int i=0; i < count; i++) {
        JSONUtilities.append(fileSelectionIndexes,i);
      }
    }
  }
  return bestFormat;
}
