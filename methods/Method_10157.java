/** 
 * Gets country, province and city of the specified IP.
 * @param ip the specified IP
 * @return address info, for example      <pre>{ "country": "", "province": "", "city": "" } </pre>, returns  {@code null} if not found
 */
public static JSONObject getAddress(final String ip){
  final String ak=Symphonys.BAIDU_LBS_AK;
  if (StringUtils.isBlank(ak) || !Strings.isIPv4(ip)) {
    return null;
  }
  HttpURLConnection conn=null;
  try {
    final URL url=new URL("http://api.map.baidu.com/location/ip?ip=" + ip + "&ak=" + ak);
    conn=(HttpURLConnection)url.openConnection();
    conn.setConnectTimeout(1000);
    conn.setReadTimeout(1000);
    final JSONObject data=new JSONObject(IOUtils.toString(conn.getInputStream(),"UTF-8"));
    if (0 != data.optInt("status")) {
      return getAddressTaobao(ip);
    }
    final String content=data.optString("address");
    final String country=content.split("\\|")[0];
    if (!"CN".equals(country) && !"HK".equals(country) && !"TW".equals(country)) {
      LOGGER.log(Level.WARN,"Found other country via Baidu [" + country + ", " + ip + "]");
      return null;
    }
    final String province=content.split("\\|")[1];
    String city=content.split("\\|")[2];
    if ("None".equals(province) || "None".equals(city)) {
      return getAddressTaobao(ip);
    }
    city=StringUtils.replace(city,"?","");
    final JSONObject ret=new JSONObject();
    ret.put(Common.COUNTRY,"??");
    ret.put(Common.PROVINCE,province);
    ret.put(Common.CITY,city);
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Can't get location from Baidu [ip=" + ip + "]",e);
    return getAddressTaobao(ip);
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
