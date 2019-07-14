private static void graph(String projectDir,List<String> srcpath,List<String> inclpaths,OutputStream symOut,OutputStream refOut) throws Exception {
  Analyzer idx=newAnalyzer(projectDir,srcpath,inclpaths);
  idx.multilineFunType=true;
  JsonFactory jsonFactory=new JsonFactory();
  JsonGenerator symJson=jsonFactory.createGenerator(symOut);
  JsonGenerator refJson=jsonFactory.createGenerator(refOut);
  JsonGenerator[] allJson={symJson,refJson};
  for (  JsonGenerator json : allJson) {
    json.writeStartArray();
  }
  for (  Binding b : idx.getAllBindings()) {
    if (b.file != null && b.file.startsWith(projectDir)) {
      writeSymJson(b,symJson);
      writeRefJson(b.node,b,refJson);
    }
    for (    Node ref : b.refs) {
      if (ref.file != null && ref.file.startsWith(projectDir)) {
        String key=ref.file + ":" + ref.start;
        if (!seenRef.contains(key)) {
          writeRefJson(ref,b,refJson);
          seenRef.add(key);
        }
      }
    }
  }
  for (  JsonGenerator json : allJson) {
    json.writeEndArray();
    json.close();
  }
}
