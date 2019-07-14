public static String escapeSingleLine(String line){
  StringBuilder escaped=new StringBuilder(line.length() + 16);
  String currentLine=line;
  if (currentLine.startsWith(QUOTE_START)) {
    escaped.append(currentLine.substring(0,1));
    currentLine=currentLine.substring(1);
  }
  int url=currentLine.indexOf(URL_START);
  while (url > -1) {
    String before=currentLine.substring(0,url);
    before=escapeBackticks(escaped,before);
    escaped.append(StringEscapeUtils.escapeHtml4(before));
    int urlEnd=currentLine.indexOf(">",url) + 1;
    escaped.append(currentLine.substring(url,urlEnd));
    currentLine=currentLine.substring(urlEnd);
    url=currentLine.indexOf(URL_START);
  }
  currentLine=escapeBackticks(escaped,currentLine);
  escaped.append(StringEscapeUtils.escapeHtml4(currentLine));
  return escaped.toString();
}
