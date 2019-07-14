/** 
 * Iterates over the file tree of a directory. It receives a visitor and will call its methods for each file in the directory. preVisitDirectory (directory) visitFile (file) - recursively the same for every subdirectory postVisitDirectory (directory)
 * @param directory the directory to iterate
 * @param visitor the visitor that will be invoked for each directory/file in the tree
 */
public static void walkFileTree(File directory,FileTreeVisitor visitor){
  visitor.preVisitDirectory(directory);
  File[] files=directory.listFiles();
  if (files != null) {
    for (    File file : files) {
      if (file.isDirectory()) {
        walkFileTree(file,visitor);
      }
 else {
        visitor.visitFile(file);
      }
    }
  }
  visitor.postVisitDirectory(directory);
}
