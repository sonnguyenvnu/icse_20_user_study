/** 
 * ?InputStream?????.
 */
public static long copyFile(InputStream from,File to) throws IOException {
  long totalBytes=0;
  FileOutputStream fos=new FileOutputStream(to,false);
  try {
    byte[] data=new byte[1024];
    int len;
    while ((len=from.read(data)) > -1) {
      fos.write(data,0,len);
      totalBytes+=len;
    }
    fos.flush();
  }
  finally {
    fos.close();
  }
  return totalBytes;
}
