/** 
 * ??????????,????????? ?????????????,?????,??????????????????
 * @param srcFile   ???
 * @param destParam ??????
 * @return ???????????
 */
private static String buildDestinationZipFilePath(File srcFile,String destParam){
  if (RxDataTool.isNullString(destParam)) {
    if (srcFile.isDirectory()) {
      destParam=srcFile.getParent() + File.separator + srcFile.getName() + ".zip";
    }
 else {
      String fileName=srcFile.getName().substring(0,srcFile.getName().lastIndexOf("."));
      destParam=srcFile.getParent() + File.separator + fileName + ".zip";
    }
  }
 else {
    createDestDirectoryIfNecessary(destParam);
    if (destParam.endsWith(File.separator)) {
      String fileName="";
      if (srcFile.isDirectory()) {
        fileName=srcFile.getName();
      }
 else {
        fileName=srcFile.getName().substring(0,srcFile.getName().lastIndexOf("."));
      }
      destParam+=fileName + ".zip";
    }
  }
  return destParam;
}
