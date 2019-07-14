/** 
 * Read a line from the given file in the builds src folder. 1-based i.e. first line has line no. 1
 * @param filePath
 * @param lineNo
 * @return the requested source line
 */
protected String getSourceLine(String filePath,int lineNo,int radius){
  if (lineNo == -1) {
    loge("invalid line number: " + lineNo,null);
    return "";
  }
  File f=new File(srcPath + File.separator + filePath);
  String output="";
  try {
    BufferedReader r=new BufferedReader(new FileReader(f));
    int i=1;
    while (i <= lineNo + radius) {
      String line=r.readLine();
      if (line == null) {
        break;
      }
      if (i >= lineNo - radius) {
        if (i > lineNo - radius) {
          output+="\n";
        }
        output+=f.getName() + ":" + i + (i == lineNo ? " =>  " : "     ") + line;
      }
      i++;
    }
    r.close();
    return output;
  }
 catch (  FileNotFoundException ex) {
    return f.getName() + ":" + lineNo;
  }
catch (  IOException ex) {
    loge("other io exception",ex);
    return "";
  }
}
