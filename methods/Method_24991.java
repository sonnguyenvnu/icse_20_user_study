/** 
 * Decodes parameters in percent-encoded URI-format ( e.g. "name=Jack%20Daniels&pass=Single%20Malt" ) and adds them to given Map.
 */
private void decodeParms(String parms,Map<String,List<String>> p){
  if (parms == null) {
    this.queryParameterString="";
    return;
  }
  this.queryParameterString=parms;
  StringTokenizer st=new StringTokenizer(parms,"&");
  while (st.hasMoreTokens()) {
    String e=st.nextToken();
    int sep=e.indexOf('=');
    String key=null;
    String value=null;
    if (sep >= 0) {
      key=NanoHTTPD.decodePercent(e.substring(0,sep)).trim();
      value=NanoHTTPD.decodePercent(e.substring(sep + 1));
    }
 else {
      key=NanoHTTPD.decodePercent(e).trim();
      value="";
    }
    List<String> values=p.get(key);
    if (values == null) {
      values=new ArrayList<String>();
      p.put(key,values);
    }
    values.add(value);
  }
}
