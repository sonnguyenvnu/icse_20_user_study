public static String getStringFromFile(File file) throws Exception {
  FileInputStream fin=new FileInputStream(file);
  String ret=convertStreamToString(fin);
  fin.close();
  return ret;
}
