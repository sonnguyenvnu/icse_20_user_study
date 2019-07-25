public void visit(StylePageLayoutPropertiesElement ele){
  StylePageLayoutProperties pageLayoutProperties=currentStyle.getPageLayoutProperties();
  if (pageLayoutProperties == null) {
    pageLayoutProperties=new StylePageLayoutProperties();
    currentStyle.setPageLayoutProperties(pageLayoutProperties);
  }
  String backgroundColor=ele.getFoBackgroundColorAttribute();
  if (StringUtils.isNotEmpty(backgroundColor)) {
  }
  String border=ele.getFoBorderAttribute();
  if (StringUtils.isNotEmpty(border)) {
  }
  String borderBottom=ele.getFoBorderBottomAttribute();
  if (StringUtils.isNotEmpty(borderBottom)) {
  }
  String borderLeft=ele.getFoBorderLeftAttribute();
  if (StringUtils.isNotEmpty(borderLeft)) {
  }
  String borderRight=ele.getFoBorderRightAttribute();
  if (StringUtils.isNotEmpty(borderRight)) {
  }
  String borderTop=ele.getFoBorderTopAttribute();
  if (StringUtils.isNotEmpty(borderTop)) {
  }
  String margin=ele.getFoMarginAttribute();
  if (StringUtils.isNotEmpty(margin)) {
    pageLayoutProperties.setMargin(ODFUtils.getDimensionAsPoint(margin));
  }
  String marginBottom=ele.getFoMarginBottomAttribute();
  if (StringUtils.isNotEmpty(marginBottom)) {
    pageLayoutProperties.setMarginBottom(ODFUtils.getDimensionAsPoint(marginBottom));
  }
  String marginLeft=ele.getFoMarginLeftAttribute();
  if (StringUtils.isNotEmpty(marginLeft)) {
    pageLayoutProperties.setMarginLeft(ODFUtils.getDimensionAsPoint(marginLeft));
  }
  String marginRight=ele.getFoMarginRightAttribute();
  if (StringUtils.isNotEmpty(marginRight)) {
    pageLayoutProperties.setMarginRight(ODFUtils.getDimensionAsPoint(marginRight));
  }
  String marginTop=ele.getFoMarginTopAttribute();
  if (StringUtils.isNotEmpty(marginTop)) {
    pageLayoutProperties.setMarginTop(ODFUtils.getDimensionAsPoint(marginTop));
  }
  String padding=ele.getFoPaddingAttribute();
  if (StringUtils.isNotEmpty(padding)) {
  }
  String paddingBottom=ele.getFoPaddingBottomAttribute();
  if (StringUtils.isNotEmpty(paddingBottom)) {
  }
  String paddingLeft=ele.getFoPaddingLeftAttribute();
  if (StringUtils.isNotEmpty(paddingLeft)) {
  }
  String paddingRight=ele.getFoPaddingRightAttribute();
  if (StringUtils.isNotEmpty(paddingRight)) {
  }
  String paddingTop=ele.getFoPaddingTopAttribute();
  if (StringUtils.isNotEmpty(paddingTop)) {
  }
  String height=ele.getFoPageHeightAttribute();
  if (StringUtils.isNotEmpty(height)) {
    pageLayoutProperties.setHeight(ODFUtils.getDimensionAsPoint(height));
  }
  String width=ele.getFoPageWidthAttribute();
  if (StringUtils.isNotEmpty(width)) {
    pageLayoutProperties.setWidth(ODFUtils.getDimensionAsPoint(width));
  }
  String orientation=ele.getStylePrintOrientationAttribute();
  if (StringUtils.isNotEmpty(orientation)) {
    if (StylePrintOrientationAttribute.Value.LANDSCAPE.toString().equals(orientation)) {
      pageLayoutProperties.setOrientation(PageOrientation.Landscape);
    }
 else     if (StylePrintOrientationAttribute.Value.PORTRAIT.toString().equals(orientation)) {
      pageLayoutProperties.setOrientation(PageOrientation.Portrait);
    }
  }
  super.visit(ele);
}
