private String readDataFromConnection(HttpURLConnection con){
  int code=0;
  URL url;
  String result;
  try {
    code=con.getResponseCode();
    url=con.getURL();
    if (code == 200) {
      StringBuilder buffer=new StringBuilder();
      BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
      String temp;
      while ((temp=br.readLine()) != null) {
        buffer.append(temp);
        buffer.append("\n");
      }
      result=buffer.toString().trim();
      LOGGER.infoWithApp(null,"uri:" + url + " return result: " + result);
    }
 else {
      LOGGER.infoWithApp(null,"uri:" + url + " return code: " + code);
      result=errorMessage;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
    return errorMessage;
  }
  return result;
}
