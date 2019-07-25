public static HtmlColorAndStyle build(ISkinParam skinParam,String definition){
  HtmlColor color=build(skinParam).getColors().get(0).color;
  LinkStyle style=LinkStyle.NORMAL();
  final IHtmlColorSet set=skinParam.getIHtmlColorSet();
  for (  String s : definition.split(",")) {
    final LinkStyle tmpStyle=LinkStyle.fromString1(s);
    if (tmpStyle.isNormal() == false) {
      style=tmpStyle;
      continue;
    }
    final HtmlColor tmpColor=set.getColorIfValid(s);
    if (tmpColor != null) {
      color=tmpColor;
    }
  }
  return new HtmlColorAndStyle(color,style);
}
