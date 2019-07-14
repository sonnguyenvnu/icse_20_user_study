/** 
 * ( begin auto-generated from PImage_resize.xml ) Resize the image to a new width and height. To make the image scale proportionally, use 0 as the value for the <b>wide</b> or <b>high</b> parameter. For instance, to make the width of an image 150 pixels, and change the height using the same proportion, use resize(150, 0).<br /> <br /> Even though a PGraphics is technically a PImage, it is not possible to rescale the image data found in a PGraphics. (It's simply not possible to do this consistently across renderers: technically infeasible with P3D, or what would it even do with PDF?) If you want to resize PGraphics content, first get a copy of its image data using the <b>get()</b> method, and call <b>resize()</b> on the PImage that is returned. ( end auto-generated )
 * @webref pimage:method
 * @brief Changes the size of an image to a new width and height
 * @usage web_application
 * @param w the resized image width
 * @param h the resized image height
 * @see PImage#get(int,int,int,int)
 */
public void resize(int w,int h){
  if (w <= 0 && h <= 0) {
    throw new IllegalArgumentException("width or height must be > 0 for resize");
  }
  if (w == 0) {
    float diff=(float)h / (float)height;
    w=(int)(width * diff);
  }
 else   if (h == 0) {
    float diff=(float)w / (float)width;
    h=(int)(height * diff);
  }
  BufferedImage img=shrinkImage((BufferedImage)getNative(),w * pixelDensity,h * pixelDensity);
  PImage temp=new PImage(img);
  this.pixelWidth=temp.width;
  this.pixelHeight=temp.height;
  this.pixels=temp.pixels;
  this.width=pixelWidth / pixelDensity;
  this.height=pixelHeight / pixelDensity;
  updatePixels();
}
