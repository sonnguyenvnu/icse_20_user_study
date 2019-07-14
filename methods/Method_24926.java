public Color verifiedHSBColor(float h,float s,float b,float a){
  if (h < 0 || h > colorMode.v1Max || s < 0 || s > colorMode.v2Max || b < 0 || b > colorMode.v3Max) {
    return colorError();
  }
  ilegalColor=false;
  Color c=Color.getHSBColor(h / colorMode.v1Max,s / colorMode.v2Max,b / colorMode.v3Max);
  return new Color(c.getRed(),c.getGreen(),c.getBlue(),255);
}
