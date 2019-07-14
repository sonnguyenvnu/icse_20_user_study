/** 
 * Function to be used by subclasses of PImage to init later than at the constructor, or re-init later when things changes. Used by Capture and Movie classes (and perhaps others), because the width/height will not be known when super() is called. (Leave this public so that other libraries can do the same.)
 */
public void init(int width,int height,int format,int factor){
  this.width=width;
  this.height=height;
  this.format=format;
  this.pixelDensity=factor;
  pixelWidth=width * pixelDensity;
  pixelHeight=height * pixelDensity;
  this.pixels=new int[pixelWidth * pixelHeight];
}
