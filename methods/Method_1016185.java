/** 
 * Displays unattached WebPopOver at the specified location.
 * @param x WebPopOver X location
 * @param y WebPopOver Y location
 * @return displayed WebPopOver
 */
public WebPopOver show(final int x,final int y){
  attached=false;
  preferredDirection=null;
  setPopupStyle(PopupStyle.simple);
  pack();
  setLocation(x - getShadeWidth(),y - getShadeWidth());
  setVisible(true);
  return this;
}
