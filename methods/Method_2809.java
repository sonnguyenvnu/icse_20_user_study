public static String readTxt(String file,String charsetName) throws IOException {
  InputStream is=IOAdapter.open(file);
  byte[] targetArray=new byte[is.available()];
  int len;
  int off=0;
  while ((len=is.read(targetArray,off,targetArray.length - off)) != -1 && off < targetArray.length) {
    off+=len;
  }
  is.close();
  return new String(targetArray,charsetName);
}
