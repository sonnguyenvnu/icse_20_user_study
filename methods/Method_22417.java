/** 
 * Synchronous version of rebuild, used when the sketchbook folder has changed, so that the libraries are properly re-scanned before those menus (and the examples window) are rebuilt.
 */
protected void rebuildSketchbookMenus(){
  for (  Mode mode : getModeList()) {
    mode.rebuildImportMenu();
    mode.rebuildToolbarMenu();
    mode.rebuildExamplesFrame();
    mode.rebuildSketchbookFrame();
  }
}
