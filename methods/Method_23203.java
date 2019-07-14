/** 
 * ( begin auto-generated from displayDensity.xml ) This function returns the number "2" if the screen is a high-density screen (called a Retina display on OS X or high-dpi on Windows and Linux) and a "1" if not. This information is useful for a program to adapt to run at double the pixel density on a screen that supports it. ( end auto-generated )
 * @webref environment
 * @see PApplet#pixelDensity(int)
 * @see PApplet#size(int,int)
 */
public int displayDensity(){
  if (display != SPAN && (fullScreen || present)) {
    return displayDensity(display);
  }
  for (int i=0; i < displayDevices.length; i++) {
    if (displayDensity(i + 1) == 2) {
      return 2;
    }
  }
  return 1;
}
