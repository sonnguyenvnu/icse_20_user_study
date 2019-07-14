/** 
 * Determines display's height.
 */
public int getDisplayHeight(){
  Display display=getWindowManager().getDefaultDisplay();
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
    return display.getHeight();
  }
 else {
    final Point size=new Point();
    display.getSize(size);
    return size.y;
  }
}
