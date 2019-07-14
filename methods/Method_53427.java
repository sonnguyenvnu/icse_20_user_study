private static void storeJSON(String rawJSON,String fileName) throws IOException {
  FileOutputStream fos=null;
  OutputStreamWriter osw=null;
  BufferedWriter bw=null;
  try {
    fos=new FileOutputStream(fileName);
    osw=new OutputStreamWriter(fos,"UTF-8");
    bw=new BufferedWriter(osw);
    bw.write(rawJSON);
    bw.flush();
  }
  finally {
    if (bw != null) {
      try {
        bw.close();
      }
 catch (      IOException ignore) {
      }
    }
    if (osw != null) {
      try {
        osw.close();
      }
 catch (      IOException ignore) {
      }
    }
    if (fos != null) {
      try {
        fos.close();
      }
 catch (      IOException ignore) {
      }
    }
  }
}
