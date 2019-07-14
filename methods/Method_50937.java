public StringBuilder getCodeBuffer(){
  StringBuilder sb=new StringBuilder();
  List<String> lines=cl.getCode();
  for (  String line : lines) {
    sb.append(line).append(PMD.EOL);
  }
  return sb;
}
