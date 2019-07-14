/** 
 * @param header
 * @param defaultValue
 */
static public void registerHttpHeader(String header,String defaultValue){
  s_headers.put(header.toLowerCase(),new HttpHeaderInfo(header,defaultValue));
}
