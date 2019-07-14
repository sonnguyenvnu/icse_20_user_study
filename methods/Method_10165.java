private static boolean containsChinese(final String str){
  if (StringUtils.isBlank(str)) {
    return false;
  }
  final Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
  final Matcher m=p.matcher(str);
  return m.find();
}
