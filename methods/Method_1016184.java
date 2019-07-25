/** 
 * Displays unattached WebPopOver at the specified screen location.
 * @param location WebPopOver location on screen
 * @return displayed WebPopOver
 */
public WebPopOver show(final PopOverLocation location){
  attached=false;
  preferredDirection=null;
  setPopupStyle(PopupStyle.simple);
  final Dimension ss=Toolkit.getDefaultToolkit().getScreenSize();
  pack();
switch (location) {
case center:
{
      setLocation(ss.width / 2 - getWidth() / 2,ss.height / 2 - getHeight() / 2);
      break;
    }
case topLeft:
{
    setLocation(0,0);
    break;
  }
case topRight:
{
  setLocation(ss.width - getWidth(),0);
  break;
}
case bottomLeft:
{
setLocation(0,ss.height - getHeight());
break;
}
case bottomRight:
{
setLocation(ss.width - getWidth(),ss.height - getHeight());
break;
}
case topCenter:
{
setLocation(ss.width / 2 - getWidth() / 2,0);
break;
}
case bottomCenter:
{
setLocation(ss.width / 2 - getWidth() / 2,ss.height - getHeight());
break;
}
case leftCenter:
{
setLocation(0,ss.height / 2 - getHeight() / 2);
break;
}
case rightCenter:
{
setLocation(ss.width - getWidth(),ss.height / 2 - getHeight() / 2);
break;
}
}
setVisible(true);
return this;
}
