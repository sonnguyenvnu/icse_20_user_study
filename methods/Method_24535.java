/** 
 * Recursive walk to get all subdirectories for font fun. Patch submitted by Matthias Breuer. (<a href="http://dev.processing.org/bugs/show_bug.cgi?id=1566">Bug 1566</a>)
 */
static protected void traverseDir(File folder,DefaultFontMapper mapper){
  File[] files=folder.listFiles();
  for (int i=0; i < files.length; i++) {
    if (files[i].isDirectory()) {
      mapper.insertDirectory(files[i].getPath());
      traverseDir(new File(files[i].getPath()),mapper);
    }
  }
}
