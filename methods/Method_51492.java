/** 
 * Retrieves the requested line from the specified file.
 * @param sourceFile the java or cpp source file
 * @param line line number to extract
 * @return a trimmed line of source code
 */
private String getLine(String sourceFile,int line){
  String code=null;
  try (BufferedReader br=new BufferedReader(getReader(sourceFile))){
    for (int i=0; line > i; i++) {
      String txt=br.readLine();
      code=txt == null ? "" : txt.trim();
    }
  }
 catch (  IOException ioErr) {
    ioErr.printStackTrace();
  }
  return code;
}
