/** 
 * ????????
 * @param path
 * @return
 */
public static String readTxt(String path){
  if (path == null)   return null;
  try {
    InputStream in=IOAdapter == null ? new FileInputStream(path) : IOAdapter.open(path);
    byte[] fileContent=new byte[in.available()];
    int read=readBytesFromOtherInputStream(in,fileContent);
    in.close();
    if (read >= 3 && fileContent[0] == -17 && fileContent[1] == -69 && fileContent[2] == -65)     return new String(fileContent,3,fileContent.length - 3,Charset.forName("UTF-8"));
    return new String(fileContent,Charset.forName("UTF-8"));
  }
 catch (  FileNotFoundException e) {
    logger.warning("???" + path + e);
    return null;
  }
catch (  IOException e) {
    logger.warning("??" + path + "??IO??" + e);
    return null;
  }
}
