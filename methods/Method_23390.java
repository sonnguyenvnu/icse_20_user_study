/** 
 * Returns a native BufferedImage from this PImage.
 */
public Object getNative(){
  loadPixels();
  int type=(format == RGB) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
  BufferedImage image=new BufferedImage(pixelWidth,pixelHeight,type);
  WritableRaster wr=image.getRaster();
  wr.setDataElements(0,0,pixelWidth,pixelHeight,pixels);
  return image;
}
