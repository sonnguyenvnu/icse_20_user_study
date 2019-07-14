/** 
 * Re-insert the Import Library menu. Added function so that other modes need not have an 'import' menu.
 * @since 3.0a6
 * @param sketchMenu the Sketch menu that's currently active
 */
public void insertImportMenu(JMenu sketchMenu){
  if (importMenuIndex != -1) {
    sketchMenu.insert(getImportMenu(),importMenuIndex);
  }
}
