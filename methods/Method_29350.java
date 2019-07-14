/** 
 * ????ip????port???????
 * @param ip    ??ip
 * @param port  ??????
 * @return  ???????true?????false?
 * @throws SSHException
 */
public static boolean isPortUsed(String ip,int port) throws SSHException {
  String psCmd="/bin/ps -ef | grep %s | grep -v grep";
  psCmd=String.format(psCmd,port);
  String psResponse=execute(ip,psCmd);
  boolean isUsed=false;
  if (StringUtils.isNotBlank(psResponse)) {
    String[] resultArr=psResponse.split(System.lineSeparator());
    for (    String resultLine : resultArr) {
      if (resultLine.contains(String.valueOf(port))) {
        isUsed=true;
        break;
      }
    }
  }
  return isUsed;
}
