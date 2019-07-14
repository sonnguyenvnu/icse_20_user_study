/** 
 * Initializes file walking. Separates input files and folders.
 */
protected void init(){
  rules.detectMode();
  todoFiles=new LinkedList<>();
  todoFolders=new LinkedList<>();
  if (pathList == null) {
    pathList=new LinkedList<>();
    return;
  }
  if (pathListOriginal == null) {
    pathListOriginal=(LinkedList<File>)pathList.clone();
  }
  String[] files=new String[pathList.size()];
  int index=0;
  Iterator<File> iterator=pathList.iterator();
  while (iterator.hasNext()) {
    File file=iterator.next();
    if (file.isFile()) {
      files[index++]=file.getAbsolutePath();
      iterator.remove();
    }
  }
  if (index != 0) {
    FilesIterator filesIterator=new FilesIterator(files);
    todoFiles.add(filesIterator);
  }
}
