/** 
 * Create an Image to be used as an offscreen drawing context, automatically doubling the size if running on a retina display.
 */
static public Image offscreenGraphics(Component comp,int width,int height){
  int m=Toolkit.isRetina() ? 2 : 1;
  return comp.createImage(m * width,m * height);
}
