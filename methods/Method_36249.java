static String getSystemProperty(String propName){
  String line;
  BufferedReader input=null;
  try {
    Process p=Runtime.getRuntime().exec("getprop " + propName);
    input=new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"),1024);
    line=input.readLine();
    input.close();
  }
 catch (  IOException ex) {
    Log.w(TAG,"Unable to read sysprop " + propName,ex);
    return null;
  }
 finally {
    IOUtils.closeQuietly(input);
  }
  return line;
}
