@Override public void visit(StyleColumnsElement ele){
  StyleSectionProperties sectionProperties=currentStyle.getSectionProperties();
  if (sectionProperties == null) {
    sectionProperties=new StyleSectionProperties();
    currentStyle.setSectionProperties(sectionProperties);
  }
  Integer columnCount=ele.getFoColumnCountAttribute();
  if (columnCount != null) {
    sectionProperties.setColumnCount(columnCount);
  }
  String columnGap=ele.getFoColumnGapAttribute();
  if (StringUtils.isNotEmpty(columnGap)) {
    sectionProperties.setColumnGap(ODFUtils.getDimensionAsPoint(columnGap));
  }
  currentColumnPropertiesList=new ArrayList<StyleColumnProperties>();
  super.visit(ele);
  currentStyle.setColumnPropertiesList(currentColumnPropertiesList);
  currentColumnPropertiesList=null;
}
