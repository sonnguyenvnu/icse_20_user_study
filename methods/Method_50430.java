private String firstLowercase(String target){
  if (StringUtils.isEmpty(target)) {
    return target;
  }
  char[] targetChar=target.toCharArray();
  targetChar[0]+=32;
  return String.valueOf(targetChar);
}
