public String getSlice(int startLine,int endLine){
  List<String> lines=cl.getCodeSlice(startLine,endLine);
  StringBuilder sb=new StringBuilder();
  for (  String line : lines) {
    if (sb.length() != 0) {
      sb.append(PMD.EOL);
    }
    sb.append(line);
  }
  return sb.toString();
}
