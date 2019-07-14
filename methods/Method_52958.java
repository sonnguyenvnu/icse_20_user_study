private String inputStreamToString(InputStream is){
  if (responseAsString == null) {
    StringBuilder buf=new StringBuilder();
    InputStreamReader isr=new InputStreamReader(is);
    BufferedReader br=new BufferedReader(isr);
    String line;
    try {
      while ((line=br.readLine()) != null) {
        buf.append(line);
      }
    }
 catch (    IOException e) {
      return null;
    }
    responseAsString=buf.toString();
  }
  return responseAsString;
}
