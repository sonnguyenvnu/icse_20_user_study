/** 
 * ??????????, ????????????
 * @param strFilePath
 * @param strBuffer
 */
public void TextToFile(final String strFilePath,final String strBuffer){
  FileWriter fileWriter=null;
  try {
    File fileText=new File(strFilePath);
    fileWriter=new FileWriter(fileText);
    fileWriter.write(strBuffer);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      fileWriter.flush();
      fileWriter.close();
    }
 catch (    IOException ex) {
      ex.printStackTrace();
    }
  }
}
