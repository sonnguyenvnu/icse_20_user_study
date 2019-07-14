/** 
 * This has a [link](http://example.com/) in [it](http://example.org/). Becomes... This has a <a href="http://example.com/">link</a> in <a href="http://example.org/">it</a>.
 */
static String toHtmlLinks(String stringIn){
  Pattern p=Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
  Matcher m=p.matcher(stringIn);
  StringBuilder sb=new StringBuilder();
  int start=0;
  while (m.find(start)) {
    sb.append(stringIn.substring(start,m.start()));
    String text=m.group(1);
    String url=m.group(2);
    sb.append("<a href=\"");
    sb.append(url);
    sb.append("\">");
    sb.append(text);
    sb.append("</a>");
    start=m.end();
  }
  sb.append(stringIn.substring(start));
  return sb.toString();
}
