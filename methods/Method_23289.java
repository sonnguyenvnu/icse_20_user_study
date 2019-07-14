/** 
 * Danger: available for advanced subclassing, but here be dragons. 
 */
protected void showSurface(){
  if (getGraphics().displayable()) {
    surface.setVisible(true);
  }
}
