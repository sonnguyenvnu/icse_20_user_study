private static boolean hasRealText(String line){
  if (StringUtils.isBlank(line)) {
    return false;
  }
  return !StringUtil.isAnyOf(line.trim(),"//","/*","/**","*","*/");
}
