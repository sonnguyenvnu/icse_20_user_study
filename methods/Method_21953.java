/** 
 * ??????.
 */
public static void createHomeFolderIfNotExisted(){
  File file=new File(getHomeFolder());
  if (!file.exists()) {
    file.mkdirs();
  }
}
