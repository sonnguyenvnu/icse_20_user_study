/** 
 * ??xml??,??????
 * @param requestUrl
 * @param requestMethod
 * @param xmlStr
 * @return
 */
public static Map<String,Object> httpXmlRequest(String requestUrl,String requestMethod,String xmlStr){
  Map<String,Object> map=new HashMap<String,Object>();
  try {
    HttpsURLConnection urlCon=(HttpsURLConnection)(new URL(requestUrl)).openConnection();
    urlCon.setDoInput(true);
    urlCon.setDoOutput(true);
    urlCon.setRequestMethod(requestMethod);
    if ("GET".equalsIgnoreCase(requestMethod)) {
      urlCon.connect();
    }
    urlCon.setRequestProperty("Content-Length",String.valueOf(xmlStr.getBytes().length));
    urlCon.setUseCaches(false);
    if (null != xmlStr) {
      OutputStream outputStream=urlCon.getOutputStream();
      outputStream.write(xmlStr.getBytes("UTF-8"));
      outputStream.flush();
      outputStream.close();
    }
    InputStream inputStream=urlCon.getInputStream();
    InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
    SAXReader reader=new SAXReader();
    Document document=reader.read(inputStreamReader);
    Element root=document.getRootElement();
    @SuppressWarnings("unchecked") List<Element> elementList=root.elements();
    for (    Element e : elementList) {
      map.put(e.getName(),e.getText());
    }
    LOG.info("????????:" + map.toString());
    inputStreamReader.close();
    inputStream.close();
    inputStream=null;
    urlCon.disconnect();
  }
 catch (  MalformedURLException e) {
    LOG.error(e.getMessage());
  }
catch (  IOException e) {
    LOG.error(e.getMessage());
  }
catch (  Exception e) {
    LOG.error(e.getMessage());
  }
  return map;
}
