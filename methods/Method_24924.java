private Color verifiedHexColor(int argb){
  int r=(argb >> 16) & 0xff;
  int g=(argb >> 8) & 0xff;
  int b=(argb & 0xff);
  ilegalColor=false;
  return new Color(r,g,b,255);
}
