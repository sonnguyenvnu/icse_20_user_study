public void visit(StylePageLayoutPropertiesElement ele){
  String styleName=null;
  String styleFamilyName=null;
  OdfStyleBase styleBase=null;
  Node parentNode=ele.getParentNode();
  if (parentNode instanceof StylePageLayoutElement) {
    styleName=((StylePageLayoutElement)parentNode).getStyleNameAttribute();
    styleFamilyName=((StylePageLayoutElement)parentNode).getFamilyName();
    styleBase=((StylePageLayoutElement)parentNode);
  }
 else {
    return;
  }
  if (generateCSSComments) {
    cssStyleSheet.setComment("style:page-layout/style:page-layout-properties @style:page-layout-name= " + styleName + ", @style:family=" + styleFamilyName + " begin");
  }
  cssStyleSheet.startCSSStyleDeclaration(computeCSSClassName(styleBase));
  String backgroundColor=ele.getFoBackgroundColorAttribute();
  if (StringUtils.isNotEmpty(backgroundColor)) {
    cssStyleSheet.setCSSProperty(BACKGROUND_COLOR,backgroundColor);
  }
  String border=ele.getFoBorderAttribute();
  if (StringUtils.isNotEmpty(border)) {
    cssStyleSheet.setCSSProperty(BORDER,border);
  }
  String borderBottom=ele.getFoBorderBottomAttribute();
  if (StringUtils.isNotEmpty(borderBottom)) {
    cssStyleSheet.setCSSProperty(BORDER_BOTTOM,borderBottom);
  }
  String borderLeft=ele.getFoBorderLeftAttribute();
  if (StringUtils.isNotEmpty(borderLeft)) {
    cssStyleSheet.setCSSProperty(BORDER_LEFT,borderLeft);
  }
  String borderRight=ele.getFoBorderRightAttribute();
  if (StringUtils.isNotEmpty(borderRight)) {
    cssStyleSheet.setCSSProperty(BORDER_RIGHT,borderRight);
  }
  String borderTop=ele.getFoBorderTopAttribute();
  if (StringUtils.isNotEmpty(borderTop)) {
    cssStyleSheet.setCSSProperty(BORDER_TOP,borderTop);
  }
  String margin=ele.getFoMarginAttribute();
  if (StringUtils.isNotEmpty(margin)) {
    cssStyleSheet.setCSSProperty(MARGIN,margin);
  }
  String marginBottom=ele.getFoMarginBottomAttribute();
  if (StringUtils.isNotEmpty(marginBottom)) {
    cssStyleSheet.setCSSProperty(MARGIN_BOTTOM,marginBottom);
  }
  String marginLeft=ele.getFoMarginLeftAttribute();
  if (StringUtils.isNotEmpty(marginLeft)) {
    cssStyleSheet.setCSSProperty(MARGIN_LEFT,marginLeft);
  }
  String marginRight=ele.getFoMarginRightAttribute();
  if (StringUtils.isNotEmpty(marginRight)) {
    cssStyleSheet.setCSSProperty(MARGIN_RIGHT,marginRight);
  }
  String marginTop=ele.getFoMarginTopAttribute();
  if (StringUtils.isNotEmpty(marginTop)) {
    cssStyleSheet.setCSSProperty(MARGIN_TOP,marginTop);
  }
  String padding=ele.getFoPaddingAttribute();
  if (StringUtils.isNotEmpty(padding)) {
    cssStyleSheet.setCSSProperty(PADDING,padding);
  }
  String paddingBottom=ele.getFoPaddingBottomAttribute();
  if (StringUtils.isNotEmpty(paddingBottom)) {
    cssStyleSheet.setCSSProperty(PADDING_BOTTOM,paddingBottom);
  }
  String paddingLeft=ele.getFoPaddingLeftAttribute();
  if (StringUtils.isNotEmpty(paddingLeft)) {
    cssStyleSheet.setCSSProperty(PADDING_LEFT,paddingLeft);
  }
  String paddingRight=ele.getFoPaddingRightAttribute();
  if (StringUtils.isNotEmpty(paddingRight)) {
    cssStyleSheet.setCSSProperty(PADDING_RIGHT,paddingRight);
  }
  String paddingTop=ele.getFoPaddingTopAttribute();
  if (StringUtils.isNotEmpty(paddingTop)) {
    cssStyleSheet.setCSSProperty(PADDING_TOP,paddingTop);
  }
  String width=ele.getFoPageWidthAttribute();
  if (StringUtils.isNotEmpty(width)) {
    cssStyleSheet.setCSSProperty(WIDTH,width);
  }
  super.visit(ele);
  cssStyleSheet.endCSSStyleDeclaration();
  if (generateCSSComments) {
    cssStyleSheet.setComment("style:page-layout/style:page-layout-properties @style:page-layout-name=" + styleName + ", @style:family=" + styleFamilyName + " end");
  }
}
