/** 
 * Finds a file inside a list of directory structures. Will also look for the file inside nested directories.
 * @param filename    the name of the file that should be found
 * @param directories the directories that will be searched
 * @return a list including all found paths to files that match the defined conditions
 */
public static List<Path> find(String filename,List<Path> directories){
  List<Path> files=new ArrayList<>();
  for (  Path dir : directories) {
    FileUtil.find(filename,dir).ifPresent(files::add);
  }
  return files;
}
