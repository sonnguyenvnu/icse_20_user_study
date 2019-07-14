/** 
 * Deletes the icky tmp folders that were left over from installs and updates in the previous run of Processing. Needed to be called only on the tools and modes sketchbook folders.
 * @param root
 */
static private void deleteTemp(File root){
  String pattern=root.getName().substring(0,4) + "\\d*" + "tmp";
  File[] possible=root.listFiles();
  if (possible != null) {
    for (    File f : possible) {
      if (f.getName().matches(pattern)) {
        Util.removeDir(f);
      }
    }
  }
}
