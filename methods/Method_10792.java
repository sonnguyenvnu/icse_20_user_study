/** 
 * ???????
 * @param color
 * @param alpha
 * @return
 */
public static int changeColorAlpha(int color,int alpha){
  int red=Color.red(color);
  int green=Color.green(color);
  int blue=Color.blue(color);
  return Color.argb(alpha,red,green,blue);
}
