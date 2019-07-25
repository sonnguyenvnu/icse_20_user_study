/** 
 * Enable/disable the overlay component if there is one.
 * @param enable whether to enable the overlay or disable it
 */
public void enable(boolean enable){
  requestedOverlay=enable;
  if (overlay != null) {
    if (enable) {
      if (!overlay.isVisible()) {
        Component component=getComponent();
        component.getBounds(bounds);
        bounds.setLocation(component.getLocationOnScreen());
        overlay.setBounds(bounds);
        Window window=getAncestorWindow(component);
        window.addComponentListener(overlayComponentAdapter);
        overlay.setVisible(true);
      }
    }
 else {
      if (overlay.isVisible()) {
        overlay.setVisible(false);
        Window window=getAncestorWindow(getComponent());
        window.removeComponentListener(overlayComponentAdapter);
      }
    }
  }
}
