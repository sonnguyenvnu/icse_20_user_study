/** 
 * ??????
 * @param result
 * @return
 */
public String checklogin(String result){
  String regEx="window.code=(\\d+)";
  Matcher matcher=CommonTools.getMatcher(regEx,result);
  if (matcher.find()) {
    return matcher.group(1);
  }
  return null;
}
