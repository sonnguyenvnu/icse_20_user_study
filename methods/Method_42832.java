/** 
 * ????????HTML????????
 * @param sParaTemp ??????
 * @param strMethod ???????????post?get
 * @param strButtonName ????????
 * @return ????HTML??
 */
public static String buildRequest(Map<String,Object> sParaTemp,String strMethod,String strButtonName,String actionUrl){
  List<String> keys=new ArrayList<String>(sParaTemp.keySet());
  StringBuffer sbHtml=new StringBuffer();
  sbHtml.append("<form id=\"rppaysubmit\" name=\"rppaysubmit\" action=\"" + actionUrl + "\" method=\"" + strMethod + "\">");
  for (int i=0; i < keys.size(); i++) {
    String name=(String)keys.get(i);
    Object object=sParaTemp.get(name);
    String value="";
    if (object != null) {
      value=(String)sParaTemp.get(name);
    }
    sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
  }
  sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
  sbHtml.append("<script>document.forms['rppaysubmit'].submit();</script>");
  return sbHtml.toString();
}
