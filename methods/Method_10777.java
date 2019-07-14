/** 
 * ????????????????
 * @param file ??
 * @return StringBuilder??
 */
public static byte[] readFile2Bytes(File file){
  if (file == null) {
    return null;
  }
  try {
    return RxDataTool.inputStream2Bytes(new FileInputStream(file));
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
    return null;
  }
}
