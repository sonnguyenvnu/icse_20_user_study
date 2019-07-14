/** 
 * Compare the contents of two  {@link File}s to determine if they are equal or not. <p> This method checks to see if the two  {@link File}s are different lengths or if they point to the same  {@link File}, before resorting to byte-by-byte comparison of the contents. <p> Code origin: Avalon
 */
public static boolean compare(final File one,final File two) throws IOException {
  boolean file1Exists=one.exists();
  if (file1Exists != two.exists()) {
    return false;
  }
  if (!file1Exists) {
    return true;
  }
  if ((!one.isFile()) || (!two.isFile())) {
    throw new IOException("Only files can be compared");
  }
  if (one.length() != two.length()) {
    return false;
  }
  if (equals(one,two)) {
    return true;
  }
  InputStream input1=null;
  InputStream input2=null;
  try {
    input1=new FileInputStream(one);
    input2=new FileInputStream(two);
    return StreamUtil.compare(input1,input2);
  }
  finally {
    StreamUtil.close(input1);
    StreamUtil.close(input2);
  }
}
