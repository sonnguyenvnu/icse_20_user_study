static public void locateSketchbookFolder(){
  String sketchbookPath=Preferences.getSketchbookPath();
  if (sketchbookPath != null) {
    sketchbookFolder=new File(sketchbookPath);
    if (!sketchbookFolder.exists()) {
      Messages.showWarning("Sketchbook folder disappeared","The sketchbook folder no longer exists.\n" + "Processing will switch to the default sketchbook\n" + "location, and create a new sketchbook folder if\n" + "necessary. Processing will then stop talking\n" + "about itself in the third person.",null);
      sketchbookFolder=null;
    }
  }
  if (sketchbookFolder == null) {
    sketchbookFolder=getDefaultSketchbookFolder();
    Preferences.setSketchbookPath(sketchbookFolder.getAbsolutePath());
    if (!sketchbookFolder.exists()) {
      sketchbookFolder.mkdirs();
    }
  }
  makeSketchbookSubfolders();
}
