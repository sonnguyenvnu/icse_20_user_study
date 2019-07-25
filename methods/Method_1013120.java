/** 
 * This should pop up a window with a title displaying a message.  However, I don't really know what it does because it's never been tested.
 * @param title
 * @param message
 */
public static void Display(String title,String message){
  Shell parent=UIHelper.getShellProvider().getShell();
  PopupMessage popup=new PopupMessage(parent,title,message);
  popup.open();
  return;
}
