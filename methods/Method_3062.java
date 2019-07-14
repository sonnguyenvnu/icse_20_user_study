/** 
 * ??????????
 * @return true ???????????????????
 */
private static boolean isDicNeedUpdate(String mainPath,String path[]){
  if (HanLP.Config.IOAdapter != null && !HanLP.Config.IOAdapter.getClass().getName().contains("com.hankcs.hanlp.corpus.io.FileIOAdapter")) {
    return false;
  }
  String binPath=mainPath + Predefine.BIN_EXT;
  File binFile=new File(binPath);
  if (!binFile.exists()) {
    return true;
  }
  long lastModified=binFile.lastModified();
  for (  String p : path) {
    File f=new File(p);
    String fileName=f.getName();
    int cut=fileName.lastIndexOf(' ');
    if (cut > 0) {
      p=f.getParent() + File.separator + fileName.substring(0,cut);
    }
    f=new File(p);
    if (f.exists() && f.lastModified() > lastModified) {
      IOUtil.deleteFile(binPath);
      logger.info("?????????????");
      return true;
    }
  }
  return false;
}
