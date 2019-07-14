static private int countLines(String str){
  String[] lines=str.split("\r\n|\n\r|\n|\r");
  return lines.length;
}
