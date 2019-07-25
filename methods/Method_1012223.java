/** 
 * Darkens a color by a given factor.
 * @param color the color to darken
 * @param factor The factor to darken the color.
 * @return darker version of specified color.
 */
public static int darker(int color,float factor){
  return Color.argb(Color.alpha(color),Math.max((int)(Color.red(color) * factor),0),Math.max((int)(Color.green(color) * factor),0),Math.max((int)(Color.blue(color) * factor),0));
}
