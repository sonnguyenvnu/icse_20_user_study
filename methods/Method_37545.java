/** 
 * Finds all files and returns list of founded files.
 */
public List<File> findAll(){
  List<File> allFiles=new ArrayList<>();
  File file;
  while ((file=nextFile()) != null) {
    allFiles.add(file);
  }
  return allFiles;
}
