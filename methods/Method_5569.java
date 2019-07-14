/** 
 * Parses a format line.
 * @param formatLine The line to parse.
 */
private void parseFormatLine(String formatLine){
  String[] values=TextUtils.split(formatLine.substring(FORMAT_LINE_PREFIX.length()),",");
  formatKeyCount=values.length;
  formatStartIndex=C.INDEX_UNSET;
  formatEndIndex=C.INDEX_UNSET;
  formatTextIndex=C.INDEX_UNSET;
  for (int i=0; i < formatKeyCount; i++) {
    String key=Util.toLowerInvariant(values[i].trim());
switch (key) {
case "start":
      formatStartIndex=i;
    break;
case "end":
  formatEndIndex=i;
break;
case "text":
formatTextIndex=i;
break;
default :
break;
}
}
if (formatStartIndex == C.INDEX_UNSET || formatEndIndex == C.INDEX_UNSET || formatTextIndex == C.INDEX_UNSET) {
formatKeyCount=0;
}
}
