public Reader asReader(){
  try {
    return new BufferedReader(new InputStreamReader(is,"UTF-8"));
  }
 catch (  java.io.UnsupportedEncodingException uee) {
    return new InputStreamReader(is);
  }
}
