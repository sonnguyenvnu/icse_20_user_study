public FilenameFilter filenameFilter(){
  if (language == null) {
    throw new IllegalStateException("Language is null.");
  }
  final FilenameFilter languageFilter=language.getFileFilter();
  final Set<String> exclusions=new HashSet<>();
  if (excludes != null) {
    FileFinder finder=new FileFinder();
    for (    File excludedFile : excludes) {
      if (excludedFile.isDirectory()) {
        List<File> files=finder.findFilesFrom(excludedFile,languageFilter,true);
        for (        File f : files) {
          exclusions.add(FileUtil.normalizeFilename(f.getAbsolutePath()));
        }
      }
 else {
        exclusions.add(FileUtil.normalizeFilename(excludedFile.getAbsolutePath()));
      }
    }
  }
  return new FilenameFilter(){
    @Override public boolean accept(    File dir,    String name){
      File f=new File(dir,name);
      if (exclusions.contains(FileUtil.normalizeFilename(f.getAbsolutePath()))) {
        System.err.println("Excluding " + f.getAbsolutePath());
        return false;
      }
      return languageFilter.accept(dir,name);
    }
  }
;
}
