/** 
 * Deletes all the modes/tools/libs that are flagged for removal.
 */
static private void deleteFlagged(File root) throws Exception {
  File[] markedForDeletion=root.listFiles(new FileFilter(){
    public boolean accept(    File folder){
      return (folder.isDirectory() && LocalContribution.isDeletionFlagged(folder));
    }
  }
);
  if (markedForDeletion != null) {
    for (    File folder : markedForDeletion) {
      Util.removeDir(folder);
    }
  }
}
