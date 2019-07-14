/** 
 * Gets chinese percentage of the specified string.
 * @param str the specified string
 * @return percentage
 */
public static int getChinesePercent(final String str){
  if (StringUtils.isBlank(str)) {
    return 0;
  }
  final Pattern p=Pattern.compile("([\u4e00-\u9fa5]+)");
  final Matcher m=p.matcher(str);
  final StringBuilder chineseBuilder=new StringBuilder();
  while (m.find()) {
    chineseBuilder.append(m.group(0));
  }
  return (int)Math.floor(StringUtils.length(chineseBuilder.toString()) / (double)StringUtils.length(str) * 100);
}
