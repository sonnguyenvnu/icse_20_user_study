private static void graph(String srcpath,String[] inclpaths,OutputStream symOut,OutputStream refOut,OutputStream docOut) throws Exception {
  List<String> parentDirs=Lists.newArrayList(inclpaths);
  parentDirs.add(dirname(srcpath));
  Collections.sort(parentDirs,new Comparator<String>(){
    @Override public int compare(    String s1,    String s2){
      int diff=s1.length() - s2.length();
      if (0 == diff) {
        return s1.compareTo(s2);
      }
      return diff;
    }
  }
);
  Analyzer idx=newAnalyzer(srcpath,inclpaths);
  idx.multilineFunType=true;
  JsonFactory jsonFactory=new JsonFactory();
  JsonGenerator symJson=jsonFactory.createGenerator(symOut);
  JsonGenerator refJson=jsonFactory.createGenerator(refOut);
  JsonGenerator docJson=jsonFactory.createGenerator(docOut);
  JsonGenerator[] allJson={symJson,refJson,docJson};
  for (  JsonGenerator json : allJson) {
    json.writeStartArray();
  }
  for (  Binding b : idx.getAllBindings()) {
    String path=b.qname.replace('.','/').replace("%20",".");
    if (b.getFile() != null) {
      if (b.getFile().startsWith(srcpath)) {
        writeSymJson(b,symJson);
        writeDocJson(b,idx,docJson);
      }
    }
    for (    Node ref : b.refs) {
      if (ref.file != null) {
        if (ref.file.startsWith(srcpath)) {
          writeRefJson(ref,b,refJson);
        }
      }
    }
  }
  for (  JsonGenerator json : allJson) {
    json.writeEndArray();
    json.close();
  }
}
