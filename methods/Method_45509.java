/** 
 * ????ip???????
 * @param whiteList ?????????
 * @param localIP   ????
 * @return ??????
 */
public static boolean isMatchIPByPattern(String whiteList,String localIP){
  if (StringUtils.isNotBlank(whiteList)) {
    if (StringUtils.ALL.equals(whiteList)) {
      return true;
    }
    for (    String ips : whiteList.replace(',',';').split(";",-1)) {
      try {
        if (ips.contains(StringUtils.ALL)) {
          String regex=ips.trim().replace(".","\\.").replace("*",".*");
          Pattern pattern=Pattern.compile(regex);
          if (pattern.matcher(localIP).find()) {
            return true;
          }
        }
 else         if (!isIPv4Host(ips)) {
          String regex=ips.trim().replace(".","\\.");
          Pattern pattern=Pattern.compile(regex);
          if (pattern.matcher(localIP).find()) {
            return true;
          }
        }
 else {
          if (ips.equals(localIP)) {
            return true;
          }
        }
      }
 catch (      Exception e) {
        LOGGER.warn("syntax of pattern {} is invalid",ips);
      }
    }
  }
  return false;
}
