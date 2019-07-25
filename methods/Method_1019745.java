@Override public void visit(StyleSectionPropertiesElement ele){
  StyleSectionProperties sectionProperties=currentStyle.getSectionProperties();
  if (sectionProperties == null) {
    sectionProperties=new StyleSectionProperties();
    currentStyle.setSectionProperties(sectionProperties);
  }
  String backgroundColor=ele.getFoBackgroundColorAttribute();
  if (StringUtils.isNotEmpty(backgroundColor)) {
    sectionProperties.setBackgroundColor(ColorRegistry.getInstance().getColor(backgroundColor));
  }
  Boolean dontBalanceTextColumns=ele.getTextDontBalanceTextColumnsAttribute();
  if (dontBalanceTextColumns != null) {
    sectionProperties.setDontBalanceTextColumns(dontBalanceTextColumns);
  }
  String marginLeft=ele.getFoMarginLeftAttribute();
  if (StringUtils.isNotEmpty(marginLeft)) {
    sectionProperties.setMarginLeft(ODFUtils.getDimensionAsPoint(marginLeft));
  }
  String marginRight=ele.getFoMarginRightAttribute();
  if (StringUtils.isNotEmpty(marginRight)) {
    sectionProperties.setMarginRight(ODFUtils.getDimensionAsPoint(marginRight));
  }
  super.visit(ele);
}
