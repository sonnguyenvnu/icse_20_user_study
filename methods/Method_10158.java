/** 
 * Gets province, city of the specified IP by Taobao API.
 * @param ip the specified IP
 * @return address info, for example      <pre>{ "province": "", "city": "" } </pre>, returns  {@code null} if not found
 */
private static JSONObject getAddressTaobao(final String ip){
  HttpURLConnection conn=null;
  try {
    final URL url=new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
    conn=(HttpURLConnection)url.openConnection();
    conn.setConnectTimeout(1000);
    conn.setReadTimeout(1000);
    final JSONObject data=new JSONObject(IOUtils.toString(conn.getInputStream(),"UTF-8"));
    if (0 != data.optInt("code")) {
      return null;
    }
    final String country=data.optString("country");
    final String province=data.optString("region");
    String city=data.optString("city");
    city=StringUtils.replace(city,"?","");
    final JSONObject ret=new JSONObject();
    ret.put(Common.COUNTRY,country);
    ret.put(Common.PROVINCE,province);
    ret.put(Common.CITY,city);
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Can't get location from Taobao [ip=" + ip + "]",e);
    return null;
  }
 finally {
    if (null != conn) {
      try {
        conn.disconnect();
      }
 catch (      final Exception e) {
        LOGGER.log(Level.ERROR,"Close HTTP connection error",e);
      }
    }
  }
}
