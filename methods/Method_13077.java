protected static Color darker(Color c){
  return new Color(Math.max((int)(c.getRed() * 0.85),0),Math.max((int)(c.getGreen() * 0.85),0),Math.max((int)(c.getBlue() * 0.85),0),c.getAlpha());
}
