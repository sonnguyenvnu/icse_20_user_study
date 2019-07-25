/** 
 * Generic method used by Mac and Unix font finders.
 * @return list of natively existing font directories{@inheritDoc}
 */
public List find(){
  List fontDirList=new java.util.ArrayList();
  String[] searchableDirectories=getSearchableDirectories();
  if (searchableDirectories != null) {
    for (int i=0; i < searchableDirectories.length; i++) {
      File fontDir=new File(searchableDirectories[i]);
      if (fontDir.exists() && fontDir.canRead()) {
        fontDirList.add(fontDir);
      }
    }
  }
  return fontDirList;
}
