/** 
 * Renders the specified content with video player if need.
 * @param content the specified content
 * @return rendered content
 */
public static final String render(final String content){
  final BeanManager beanManager=BeanManager.getInstance();
  final LangPropsService langPropsService=beanManager.getReference(LangPropsService.class);
  final StringBuffer contentBuilder=new StringBuffer();
  final Matcher m=PATTERN.matcher(content);
  while (m.find()) {
    final String g=m.group();
    String videoURL=StringUtils.substringBetween(g,"href=\"","\" rel=");
    if (StringUtils.isBlank(videoURL)) {
      videoURL=StringUtils.substringBetween(g,"href=\"","\"");
    }
    m.appendReplacement(contentBuilder,"<video width=\"100%\" src=\"" + videoURL + "\" controls=\"controls\">" + langPropsService.get("notSupportPlayLabel") + "</video>\n");
  }
  m.appendTail(contentBuilder);
  return contentBuilder.toString();
}
