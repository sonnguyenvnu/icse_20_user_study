/** 
 * ( begin auto-generated from createImage.xml ) Creates a new PImage (the datatype for storing images). This provides a fresh buffer of pixels to play with. Set the size of the buffer with the <b>width</b> and <b>height</b> parameters. The <b>format</b> parameter defines how the pixels are stored. See the PImage reference for more information. <br/> <br/> Be sure to include all three parameters, specifying only the width and height (but no format) will produce a strange error. <br/> <br/> Advanced users please note that createImage() should be used instead of the syntax <tt>new PImage()</tt>. ( end auto-generated ) <h3>Advanced</h3> Preferred method of creating new PImage objects, ensures that a reference to the parent PApplet is included, which makes save() work without needing an absolute path.
 * @webref image
 * @param w width in pixels
 * @param h height in pixels
 * @param format Either RGB, ARGB, ALPHA (grayscale alpha channel)
 * @see PImage
 * @see PGraphics
 */
public PImage createImage(int w,int h,int format){
  PImage image=new PImage(w,h,format);
  image.parent=this;
  return image;
}
