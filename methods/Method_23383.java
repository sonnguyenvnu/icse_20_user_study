/** 
 * ( begin auto-generated from saturation.xml ) Extracts the saturation value from a color. ( end auto-generated )
 * @webref color:creating_reading
 * @usage web_application
 * @param rgb any value of the color datatype
 * @see PGraphics#red(int)
 * @see PGraphics#green(int)
 * @see PGraphics#blue(int)
 * @see PGraphics#alpha(int)
 * @see PGraphics#hue(int)
 * @see PGraphics#brightness(int)
 */
public final float saturation(int rgb){
  if (rgb != cacheHsbKey) {
    Color.RGBtoHSB((rgb >> 16) & 0xff,(rgb >> 8) & 0xff,rgb & 0xff,cacheHsbValue);
    cacheHsbKey=rgb;
  }
  return cacheHsbValue[1] * colorModeY;
}
