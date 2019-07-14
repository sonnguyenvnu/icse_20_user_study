static public List<Library> list(File folder){
  List<Library> libraries=new ArrayList<>();
  List<File> librariesFolders=new ArrayList<>();
  librariesFolders.addAll(discover(folder));
  for (  File baseFolder : librariesFolders) {
    libraries.add(new Library(baseFolder));
  }
  String[] folderNames=folder.list(junkFolderFilter);
  if (folderNames != null) {
    for (    String subfolderName : folderNames) {
      File subfolder=new File(folder,subfolderName);
      if (!librariesFolders.contains(subfolder)) {
        List<File> discoveredLibFolders=discover(subfolder);
        for (        File discoveredFolder : discoveredLibFolders) {
          libraries.add(new Library(discoveredFolder,subfolderName));
        }
      }
    }
  }
  return libraries;
}
