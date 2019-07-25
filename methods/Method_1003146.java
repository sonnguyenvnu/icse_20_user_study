/** 
 * Repair a store by rolling back to the newest good version.
 * @param fileName the file name
 */
public static void repair(String fileName){
  PrintWriter pw=new PrintWriter(System.out);
  long version=Long.MAX_VALUE;
  OutputStream ignore=new OutputStream(){
    @Override public void write(    int b) throws IOException {
    }
  }
;
  while (version >= 0) {
    pw.println(version == Long.MAX_VALUE ? "Trying latest version" : ("Trying version " + version));
    pw.flush();
    version=rollback(fileName,version,new PrintWriter(ignore));
    try {
      String error=info(fileName + ".temp",new PrintWriter(ignore));
      if (error == null) {
        FilePath.get(fileName).moveTo(FilePath.get(fileName + ".back"),true);
        FilePath.get(fileName + ".temp").moveTo(FilePath.get(fileName),true);
        pw.println("Success");
        break;
      }
      pw.println("    ... failed: " + error);
    }
 catch (    Exception e) {
      pw.println("Fail: " + e.getMessage());
      pw.flush();
    }
    version--;
  }
  pw.flush();
}
