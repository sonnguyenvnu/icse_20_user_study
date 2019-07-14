public static String getText(InputStream is){
  StringBuilder sb=new StringBuilder();
  char[] charBuffer=new char[8192];
  int nbCharRead;
  try (BufferedReader reader=new BufferedReader(new InputStreamReader(is))){
    while ((nbCharRead=reader.read(charBuffer)) != -1) {
      sb.append(charBuffer,0,nbCharRead);
    }
  }
 catch (  IOException e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  return sb.toString();
}
