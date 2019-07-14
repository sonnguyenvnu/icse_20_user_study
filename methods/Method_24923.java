private Color verifiedGrayColor(float gray){
  if (gray < 0 || gray > colorMode.v1Max) {
    return colorError();
  }
  ilegalColor=false;
  gray=gray / colorMode.v1Max * 255;
  return new Color((int)gray,(int)gray,(int)gray,255);
}
