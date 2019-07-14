/** 
 * ?? ????? ?? ?? ?? ???
 * @param fileAbsolutePath ?????
 * @param suffix           ????
 * @return
 */
public static Vector<String> GetAllFileName(String fileAbsolutePath,String suffix){
  Vector<String> vecFile=new Vector<String>();
  File file=new File(fileAbsolutePath);
  File[] subFile=file.listFiles();
  for (int iFileLength=0; iFileLength < subFile.length; iFileLength++) {
    if (!subFile[iFileLength].isDirectory()) {
      String filename=subFile[iFileLength].getName();
      if (filename.trim().toLowerCase().endsWith(suffix)) {
        vecFile.add(filename);
      }
    }
  }
  return vecFile;
}
