private List<List<Object>> createLinesFromDocs(boolean flat,List<Map<String,Object>> docsAsMap,List<String> headers){
  List<List<Object>> objectLines=new ArrayList<>();
  for (  Map<String,Object> doc : docsAsMap) {
    List<Object> lines=new ArrayList<>();
    for (    String header : headers) {
      lines.add(findFieldValue(header,doc,flat));
    }
    objectLines.add(lines);
  }
  return objectLines;
}
