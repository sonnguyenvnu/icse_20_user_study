public static TextBlock title(FontConfiguration font,Display stringsToDisplay,ISkinParam skinParam){
  UStroke stroke=skinParam.getThickness(LineParam.titleBorder,null);
  final Rose rose=new Rose();
  HtmlColor borderColor=rose.getHtmlColor(skinParam,ColorParam.titleBorder);
  final HtmlColor backgroundColor=rose.getHtmlColor(skinParam,ColorParam.titleBackground);
  final TextBlockTitle result=new TextBlockTitle(font,stringsToDisplay,skinParam);
  if (stroke == null && borderColor == null) {
    return result;
  }
  if (stroke == null) {
    stroke=new UStroke(1.5);
  }
  if (borderColor == null) {
    borderColor=HtmlColorUtils.BLACK;
  }
  final double corner=skinParam.getRoundCorner(CornerParam.titleBorder,null);
  return withMargin(bordered(result,stroke,borderColor,backgroundColor,corner),2,2);
}
