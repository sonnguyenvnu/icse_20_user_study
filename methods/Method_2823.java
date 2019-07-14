public static BufferedWriter newBufferedWriter(String path,boolean append) throws FileNotFoundException, UnsupportedEncodingException {
  return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,append),"UTF-8"));
}
