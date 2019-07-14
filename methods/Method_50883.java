private void addDirectory(File dir,boolean recurse) throws IOException {
  if (!dir.exists()) {
    throw new FileNotFoundException("Couldn't find directory " + dir);
  }
  FileFinder finder=new FileFinder();
  add(finder.findFilesFrom(dir,configuration.filenameFilter(),recurse));
}
