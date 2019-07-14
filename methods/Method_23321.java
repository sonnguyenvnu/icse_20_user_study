/** 
 * ( begin auto-generated from textureMode.xml ) Sets the coordinate space for texture mapping. There are two options, IMAGE, which refers to the actual coordinates of the image, and NORMAL, which refers to a normalized space of values ranging from 0 to 1. The default mode is IMAGE. In IMAGE, if an image is 100 x 200 pixels, mapping the image onto the entire size of a quad would require the points (0,0) (0,100) (100,200) (0,200). The same mapping in NORMAL_SPACE is (0,0) (0,1) (1,1) (0,1). ( end auto-generated )
 * @webref image:textures
 * @param mode either IMAGE or NORMAL
 * @see PGraphics#texture(PImage)
 * @see PGraphics#textureWrap(int)
 */
public void textureMode(int mode){
  if (mode != IMAGE && mode != NORMAL) {
    throw new RuntimeException("textureMode() only supports IMAGE and NORMAL");
  }
  this.textureMode=mode;
}
