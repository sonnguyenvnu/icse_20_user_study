/** 
 * Returns IP address as integer.
 */
public static int getIpAsInt(final String ipAddress){
  int ipIntValue=0;
  String[] tokens=StringUtil.splitc(ipAddress,'.');
  for (  String token : tokens) {
    if (ipIntValue > 0) {
      ipIntValue<<=8;
    }
    ipIntValue+=Integer.parseInt(token);
  }
  return ipIntValue;
}
