/** 
 * null for nothing in setup(), 0 for noSmooth(), N for smooth(N) 
 */
boolean hasOldSyntax(){
  if (width.equals("screenWidth") || width.equals("screenHeight") || height.equals("screenHeight") || height.equals("screenWidth")) {
    final String message="The screenWidth and screenHeight variables are named\n" + "displayWidth and displayHeight in Processing 3.\n" + "Or you can use the fullScreen() method instead of size().";
    Messages.showWarning("Time for a quick update",message,null);
    return true;
  }
  if (width.equals("screen.width") || width.equals("screen.height") || height.equals("screen.height") || height.equals("screen.width")) {
    final String message="The screen.width and screen.height variables are named\n" + "displayWidth and displayHeight in Processing 3.\n" + "Or you can use the fullScreen() method instead of size().";
    Messages.showWarning("Time for a quick update",message,null);
    return true;
  }
  return false;
}
