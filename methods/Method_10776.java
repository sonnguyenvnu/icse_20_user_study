/** 
 * ?????????????????? <p>?????</p>
 * @param dir      ??
 * @param fileName ???
 * @return ????
 */
public static List<File> searchFileInDir(File dir,String fileName){
  if (dir == null || !isDir(dir)) {
    return null;
  }
  List<File> list=new ArrayList<>();
  File[] files=dir.listFiles();
  for (  File file : files) {
    if (file.getName().toUpperCase().equals(fileName.toUpperCase())) {
      list.add(file);
    }
    if (file.isDirectory()) {
      list.addAll(listFilesInDirWithFilter(file,fileName));
    }
  }
  return list;
}
