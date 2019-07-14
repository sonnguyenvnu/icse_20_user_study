/** 
 * ??????
 * @param folder   ??
 * @param fileList ????
 */
private static void enumerate(File folder,List<File> fileList){
  File[] fileArray=folder.listFiles();
  if (fileArray != null) {
    for (    File file : fileArray) {
      if (file.isFile() && !file.getName().startsWith(".")) {
        fileList.add(file);
      }
 else {
        enumerate(file,fileList);
      }
    }
  }
}
