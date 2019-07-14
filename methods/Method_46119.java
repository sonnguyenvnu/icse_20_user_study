private String httpGet(String path){
  HttpURLConnection con=null;
  String result=null;
  try {
    URL url=baseURI.resolve(path).toURL();
    con=createConnection(url,"GET",false);
    con.connect();
    result=readDataFromConnection(con);
  }
 catch (  Exception e) {
    LOGGER.errorWithApp(null,"uri:" + path + " return error: " + e.getMessage());
    result=errorMessage;
  }
 finally {
    if (con != null) {
      con.disconnect();
    }
  }
  return result;
}
