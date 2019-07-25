/** 
 * Darkens a given color.
 * @param base   base color
 * @param amount amount between 0 and 100
 * @return darken color
 */
private static int darken(int base,int amount){
  float[] hsv=new float[3];
  Color.colorToHSV(base,hsv);
  float[] hsl=hsv2hsl(hsv);
  hsl[2]-=amount / 100f;
  if (hsl[2] < 0)   hsl[2]=0f;
  hsv=hsl2hsv(hsl);
  return Color.HSVToColor(hsv);
}
