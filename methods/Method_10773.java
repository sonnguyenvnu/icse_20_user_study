/** 
 * ???????????suffix???????? <p>?????</p>
 * @param dir    ??
 * @param suffix ???
 * @return ????
 */
public static List<File> listFilesInDirWithFilter(File dir,String suffix){
  if (dir == null || !isDir(dir)) {
    return null;
  }
  List<File> list=new ArrayList<>();
  File[] files=dir.listFiles();
  for (  File file : files) {
    if (file.getName().toUpperCase().endsWith(suffix.toUpperCase())) {
      list.add(file);
    }
    if (file.isDirectory()) {
      list.addAll(listFilesInDirWithFilter(file,suffix));
    }
  }
  return list;
}
