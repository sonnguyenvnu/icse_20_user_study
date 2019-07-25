private static Color shade(Color pColor,double pBrightness){
  return new Color((int)(pColor.getRed() * pBrightness),(int)(pColor.getGreen() * pBrightness),(int)(pColor.getBlue() * pBrightness));
}
