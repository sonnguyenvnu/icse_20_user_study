/** 
 * ??????????????
 * @param path ???
 * @return ????
 */
public static List<File> fileList(String path){
  List<File> fileList=new LinkedList<File>();
  File folder=new File(path);
  if (folder.isDirectory())   enumerate(folder,fileList);
 else   fileList.add(folder);
  return fileList;
}
