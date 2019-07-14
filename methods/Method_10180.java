/** 
 * Renders the specified content with MP3 player if need.
 * @param content the specified content
 * @return rendered content
 */
public static final String render(final String content){
  final StringBuffer contentBuilder=new StringBuffer();
  final Matcher m=PATTERN.matcher(content);
  while (m.find()) {
    final String g=m.group();
    String mp3Name=StringUtils.substringBetween(g,"\">",".mp3</a>");
    String mp3URL=StringUtils.substringBetween(g,"href=\"","\" rel=");
    if (StringUtils.isBlank(mp3URL)) {
      mp3URL=StringUtils.substringBetween(g,"href=\"","\"");
    }
    m.appendReplacement(contentBuilder,"<div class=\"aplayer content-audio\" data-title=\"" + mp3Name + "\" data-url=\"" + mp3URL + "\" ></div>\n");
  }
  m.appendTail(contentBuilder);
  return contentBuilder.toString();
}
