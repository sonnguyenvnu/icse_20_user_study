/** 
 * Sign Coinbene private API request parameters. <p>Docs: <a href="https://github.com/Coinbene/API-Documents/wiki/0.1.0-Sign-Verification">
 * @param params parameters
 * @return the sign
 */
public static void signParams(Map<String,String> params){
  String requestString=params.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).map(e -> e.getKey() + "=" + e.getValue()).map(String::toUpperCase).collect(Collectors.joining("&"));
  String sign=md5(requestString).toLowerCase();
  params.put("sign",sign);
  params.remove("secret");
}
