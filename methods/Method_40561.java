public static String getSystemTempDir(){
  String tmp=System.getProperty("java.io.tmpdir");
  String sep=System.getProperty("file.separator");
  if (tmp.endsWith(sep)) {
    return tmp;
  }
  return tmp + sep;
}
