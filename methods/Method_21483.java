private List<String> createCSVLinesFromDocs(boolean flat,String separator,List<Map<String,Object>> docsAsMap,List<String> headers){
  List<String> csvLines=new ArrayList<>();
  for (  Map<String,Object> doc : docsAsMap) {
    String line="";
    for (    String header : headers) {
      line+=findFieldValue(header,doc,flat,separator);
    }
    csvLines.add(line.substring(0,line.lastIndexOf(separator)));
  }
  return csvLines;
}
