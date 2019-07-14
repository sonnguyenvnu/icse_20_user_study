/** 
 * Sketchbook has changed, update it on next viewing. 
 */
public void rebuildSketchbookFrame(){
  if (sketchbookFrame != null) {
    boolean visible=sketchbookFrame.isVisible();
    Rectangle bounds=null;
    if (visible) {
      bounds=sketchbookFrame.getBounds();
      sketchbookFrame.setVisible(false);
      sketchbookFrame.dispose();
    }
    sketchbookFrame=null;
    if (visible) {
      showSketchbookFrame();
      sketchbookFrame.setBounds(bounds);
    }
  }
}
