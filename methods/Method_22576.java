static public List<File> discover(File folder){
  List<File> libraries=new ArrayList<>();
  String[] folderNames=folder.list(junkFolderFilter);
  if (folderNames != null) {
    Arrays.sort(folderNames,String.CASE_INSENSITIVE_ORDER);
    for (    String potentialName : folderNames) {
      File baseFolder=new File(folder,potentialName);
      File libraryFolder=new File(baseFolder,"library");
      File libraryJar=new File(libraryFolder,potentialName + ".jar");
      if (libraryJar.exists()) {
        String sanityCheck=Sketch.sanitizeName(potentialName);
        if (sanityCheck.equals(potentialName)) {
          libraries.add(baseFolder);
        }
 else {
          String mess="The library \"" + potentialName + "\" cannot be used.\n" + "Library names must contain only basic letters and numbers.\n" + "(ASCII only and no spaces, and it cannot start with a number)";
          Messages.showMessage("Ignoring bad library name",mess);
          continue;
        }
      }
    }
  }
  return libraries;
}
