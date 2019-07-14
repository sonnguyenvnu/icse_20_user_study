static void rankFormats(ImportingJob job,final String bestFormat,ArrayNode rankedFormats){
  final Map<String,String[]> formatToSegments=new HashMap<String,String[]>();
  boolean download=bestFormat == null ? true : ImportingManager.formatToRecord.get(bestFormat).download;
  List<String> formats=new ArrayList<String>(ImportingManager.formatToRecord.keySet().size());
  for (  String format : ImportingManager.formatToRecord.keySet()) {
    Format record=ImportingManager.formatToRecord.get(format);
    if (record.uiClass != null && record.parser != null && record.download == download) {
      formats.add(format);
      formatToSegments.put(format,format.split("/"));
    }
  }
  if (bestFormat == null) {
    Collections.sort(formats);
  }
 else {
    Collections.sort(formats,new Comparator<String>(){
      @Override public int compare(      String format1,      String format2){
        if (format1.equals(bestFormat)) {
          return -1;
        }
 else         if (format2.equals(bestFormat)) {
          return 1;
        }
 else {
          return compareBySegments(format1,format2);
        }
      }
      int compareBySegments(      String format1,      String format2){
        int c=commonSegments(format2) - commonSegments(format1);
        return c != 0 ? c : format1.compareTo(format2);
      }
      int commonSegments(      String format){
        String[] bestSegments=formatToSegments.get(bestFormat);
        String[] segments=formatToSegments.get(format);
        if (bestSegments == null || segments == null) {
          return 0;
        }
 else {
          int i;
          for (i=0; i < bestSegments.length && i < segments.length; i++) {
            if (!bestSegments[i].equals(segments[i])) {
              break;
            }
          }
          return i;
        }
      }
    }
);
  }
  for (  String format : formats) {
    rankedFormats.add(format);
  }
}
