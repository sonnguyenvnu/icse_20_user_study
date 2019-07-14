/** 
 * ( begin auto-generated from PImage_blend.xml ) Blends a region of pixels into the image specified by the <b>img</b> parameter. These copies utilize full alpha channel support and a choice of the following modes to blend the colors of source pixels (A) with the ones of pixels in the destination image (B):<br /> <br /> BLEND - linear interpolation of colours: C = A*factor + B<br /> <br /> ADD - additive blending with white clip: C = min(A*factor + B, 255)<br /> <br /> SUBTRACT - subtractive blending with black clip: C = max(B - A*factor, 0)<br /> <br /> DARKEST - only the darkest colour succeeds: C = min(A*factor, B)<br /> <br /> LIGHTEST - only the lightest colour succeeds: C = max(A*factor, B)<br /> <br /> DIFFERENCE - subtract colors from underlying image.<br /> <br /> EXCLUSION - similar to DIFFERENCE, but less extreme.<br /> <br /> MULTIPLY - Multiply the colors, result will always be darker.<br /> <br /> SCREEN - Opposite multiply, uses inverse values of the colors.<br /> <br /> OVERLAY - A mix of MULTIPLY and SCREEN. Multiplies dark values, and screens light values.<br /> <br /> HARD_LIGHT - SCREEN when greater than 50% gray, MULTIPLY when lower.<br /> <br /> SOFT_LIGHT - Mix of DARKEST and LIGHTEST. Works like OVERLAY, but not as harsh.<br /> <br /> DODGE - Lightens light tones and increases contrast, ignores darks. Called "Color Dodge" in Illustrator and Photoshop.<br /> <br /> BURN - Darker areas are applied, increasing contrast, ignores lights. Called "Color Burn" in Illustrator and Photoshop.<br /> <br /> All modes use the alpha information (highest byte) of source image pixels as the blending factor. If the source and destination regions are different sizes, the image will be automatically resized to match the destination size. If the <b>srcImg</b> parameter is not used, the display window is used as the source image.<br /> <br /> As of release 0149, this function ignores <b>imageMode()</b>. ( end auto-generated )
 * @webref image:pixels
 * @brief  Copies a pixel or rectangle of pixels using different blending modes
 * @param src an image variable referring to the source image
 * @param sx X coordinate of the source's upper left corner
 * @param sy Y coordinate of the source's upper left corner
 * @param sw source image width
 * @param sh source image height
 * @param dx X coordinate of the destinations's upper left corner
 * @param dy Y coordinate of the destinations's upper left corner
 * @param dw destination image width
 * @param dh destination image height
 * @param mode Either BLEND, ADD, SUBTRACT, LIGHTEST, DARKEST, DIFFERENCE, EXCLUSION, MULTIPLY, SCREEN, OVERLAY, HARD_LIGHT, SOFT_LIGHT, DODGE, BURN
 * @see PApplet#alpha(int)
 * @see PImage#copy(PImage,int,int,int,int,int,int,int,int)
 * @see PImage#blendColor(int,int,int)
 */
public void blend(PImage src,int sx,int sy,int sw,int sh,int dx,int dy,int dw,int dh,int mode){
  int sx2=sx + sw;
  int sy2=sy + sh;
  int dx2=dx + dw;
  int dy2=dy + dh;
  loadPixels();
  if (src == this) {
    if (intersect(sx,sy,sx2,sy2,dx,dy,dx2,dy2)) {
      blit_resize(get(sx,sy,sw,sh),0,0,sw,sh,pixels,pixelWidth,pixelHeight,dx,dy,dx2,dy2,mode);
    }
 else {
      blit_resize(src,sx,sy,sx2,sy2,pixels,pixelWidth,pixelHeight,dx,dy,dx2,dy2,mode);
    }
  }
 else {
    src.loadPixels();
    blit_resize(src,sx,sy,sx2,sy2,pixels,pixelWidth,pixelHeight,dx,dy,dx2,dy2,mode);
  }
  updatePixels();
}
