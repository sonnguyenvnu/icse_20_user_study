/** 
 * ???????host and port
 * @param hostAndPort ?????
 * @return ??????
 */
public static String[] splitAddress(String hostAndPort){
  if (hostAndPort.indexOf(':') == -1) {
    throw new IllegalStateException("non exists port");
  }
  String[] result=hostAndPort.split(":");
  if (StringUtils.isEmpty(result[0])) {
    result[0]="0.0.0.0";
    return result;
  }
  if (result[0].charAt(0) == '/') {
    result[0]=result[0].substring(1);
    return result;
  }
  return result;
}
