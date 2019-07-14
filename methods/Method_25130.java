private List<String> getLines(int startLine,int endLine){
  LineNumberReader reader=new LineNumberReader(new StringReader(sourceBuilder.toString()));
  List<String> lines=new ArrayList<>(endLine - startLine + 1);
  String line;
  try {
    while ((line=reader.readLine()) != null) {
      if (reader.getLineNumber() >= startLine) {
        lines.add(line);
      }
      if (reader.getLineNumber() >= endLine) {
        break;
      }
    }
    return lines;
  }
 catch (  IOException e) {
    throw new AssertionError("Wrapped StringReader should not produce I/O exceptions");
  }
}
