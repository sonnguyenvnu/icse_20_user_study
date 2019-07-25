/** 
 * ??
 * @param zipFileName ?????zip????--???,???null????????????????
 * @param relativePath ?????????
 * @param directory ??????????
 * @throws FileNotFoundException
 * @throws IOException
 */
public static String zip(String zipFileName,String relativePath,String directory) throws FileNotFoundException, IOException {
  String fileName=zipFileName;
  if (fileName == null || fileName.trim().equals("")) {
    File temp=new File(directory);
    if (temp.isDirectory()) {
      fileName=directory + ".zip";
    }
 else {
      if (directory.indexOf(".") > 0) {
        fileName=directory.substring(0,directory.lastIndexOf(".")) + ".zip";
      }
 else {
        fileName=directory + ".zip";
      }
    }
  }
  ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(fileName));
  try {
    zip(zos,relativePath,directory);
  }
 catch (  IOException ex) {
    throw ex;
  }
 finally {
    if (null != zos) {
      zos.close();
    }
  }
  return fileName;
}
