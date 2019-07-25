public HtmlColorSimple opposite(){
  final Color mono=new ColorChangerMonochrome().getChangedColor(color);
  final int grayScale=255 - mono.getGreen() > 127 ? 255 : 0;
  return new HtmlColorSimple(new Color(grayScale,grayScale,grayScale),true);
}
