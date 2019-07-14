private static String firstDelete(String target){
  if (StringUtils.isEmpty(target)) {
    return target;
  }
  return target.substring(1,target.length());
}
