/** 
 * Installs all the modes/tools whose installation failed during an auto-update the previous time Processing was started up.
 */
static private void installPreviouslyFailed(Base base,File root) throws Exception {
  File[] installList=root.listFiles(new FileFilter(){
    public boolean accept(    File folder){
      return folder.isFile();
    }
  }
);
  for (  File file : installList) {
    for (    AvailableContribution contrib : listing.advertisedContributions) {
      if (file.getName().equals(contrib.getName())) {
        file.delete();
        installOnStartUp(base,contrib);
        EventQueue.invokeAndWait(() -> {
          listing.replaceContribution(contrib,contrib);
        }
);
      }
    }
  }
}
