private static String readFirstLine(File fileName) throws IOException {
  FileInputStream fis=null;
  InputStreamReader isr=null;
  BufferedReader br=null;
  try {
    fis=new FileInputStream(fileName);
    isr=new InputStreamReader(fis,"UTF-8");
    br=new BufferedReader(isr);
    return br.readLine();
  }
  finally {
    if (br != null) {
      try {
        br.close();
      }
 catch (      IOException ignore) {
      }
    }
    if (isr != null) {
      try {
        isr.close();
      }
 catch (      IOException ignore) {
      }
    }
    if (fis != null) {
      try {
        fis.close();
      }
 catch (      IOException ignore) {
      }
    }
  }
}
