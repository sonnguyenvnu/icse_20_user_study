/** 
 * ?????????????“??=???”????“&”????????
 * @param params ???????????????
 * @return ??????
 */
public static String createLinkString(Map<String,String> params){
  List<String> keys=new ArrayList<String>(params.keySet());
  Collections.sort(keys);
  String prestr="";
  for (int i=0; i < keys.size(); i++) {
    String key=keys.get(i);
    String value=params.get(key);
    if (i == keys.size() - 1) {
      prestr=prestr + key + "=" + value;
    }
 else {
      prestr=prestr + key + "=" + value + "&";
    }
  }
  return prestr;
}
