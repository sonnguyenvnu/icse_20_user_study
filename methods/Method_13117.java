static protected Color infoColor(Color c){
  if (c.getRed() + c.getGreen() + c.getBlue() > (3 * 127)) {
    return new Color((int)((c.getRed() - 127) * 0.7 + 127),(int)((c.getGreen() - 127) * 0.7 + 127),(int)((c.getBlue() - 127) * 0.7 + 127),c.getAlpha());
  }
 else {
    return new Color((int)(127 - (127 - c.getRed()) * 0.7),(int)(127 - (127 - c.getGreen()) * 0.7),(int)(127 - (127 - c.getBlue()) * 0.7),c.getAlpha());
  }
}
