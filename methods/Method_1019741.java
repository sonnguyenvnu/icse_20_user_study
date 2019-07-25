@Override public void visit(StyleTableCellPropertiesElement ele){
  StyleTableCellProperties tableCellProperties=currentStyle.getTableCellProperties();
  if (tableCellProperties == null) {
    tableCellProperties=new StyleTableCellProperties();
    currentStyle.setTableCellProperties(tableCellProperties);
  }
  String backgroundColor=ele.getFoBackgroundColorAttribute();
  if (StringUtils.isNotEmpty(backgroundColor)) {
    tableCellProperties.setBackgroundColor(ColorRegistry.getInstance().getColor(backgroundColor));
  }
  String border=ele.getFoBorderAttribute();
  if (StringUtils.isNotEmpty(border)) {
    tableCellProperties.setBorder(new StyleBorder(border,BorderType.ALL));
  }
  String borderBottom=ele.getFoBorderBottomAttribute();
  if (StringUtils.isNotEmpty(borderBottom)) {
    tableCellProperties.setBorderBottom(new StyleBorder(borderBottom,BorderType.BOTTOM));
  }
  String borderLeft=ele.getFoBorderLeftAttribute();
  if (StringUtils.isNotEmpty(borderLeft)) {
    tableCellProperties.setBorderLeft(new StyleBorder(borderLeft,BorderType.LEFT));
  }
  String borderRight=ele.getFoBorderRightAttribute();
  if (StringUtils.isNotEmpty(borderRight)) {
    tableCellProperties.setBorderRight(new StyleBorder(borderRight,BorderType.RIGHT));
  }
  String borderTop=ele.getFoBorderTopAttribute();
  if (StringUtils.isNotEmpty(borderTop)) {
    tableCellProperties.setBorderTop(new StyleBorder(borderTop,BorderType.TOP));
  }
  String padding=ele.getFoPaddingAttribute();
  if (StringUtils.isNotEmpty(padding)) {
    tableCellProperties.setPadding(ODFUtils.getDimensionAsPoint(padding));
  }
  String paddingBottom=ele.getFoPaddingBottomAttribute();
  if (StringUtils.isNotEmpty(paddingBottom)) {
    tableCellProperties.setPaddingBottom(ODFUtils.getDimensionAsPoint(paddingBottom));
  }
  String paddingLeft=ele.getFoPaddingLeftAttribute();
  if (StringUtils.isNotEmpty(paddingLeft)) {
    tableCellProperties.setPaddingLeft(ODFUtils.getDimensionAsPoint(paddingLeft));
  }
  String paddingRight=ele.getFoPaddingRightAttribute();
  if (StringUtils.isNotEmpty(paddingRight)) {
    tableCellProperties.setPaddingRight(ODFUtils.getDimensionAsPoint(paddingRight));
  }
  String paddingTop=ele.getFoPaddingTopAttribute();
  if (StringUtils.isNotEmpty(paddingTop)) {
    tableCellProperties.setPaddingTop(ODFUtils.getDimensionAsPoint(paddingTop));
  }
  String verticalAlign=ele.getStyleVerticalAlignAttribute();
  if (StringUtils.isNotEmpty(verticalAlign)) {
    if (StyleVerticalAlignAttribute.Value.BASELINE.toString().equals(verticalAlign)) {
      tableCellProperties.setVerticalAlignment(Element.ALIGN_BASELINE);
    }
 else     if (StyleVerticalAlignAttribute.Value.TOP.toString().equals(verticalAlign)) {
      tableCellProperties.setVerticalAlignment(Element.ALIGN_TOP);
    }
 else     if (StyleVerticalAlignAttribute.Value.MIDDLE.toString().equals(verticalAlign)) {
      tableCellProperties.setVerticalAlignment(Element.ALIGN_MIDDLE);
    }
 else     if (StyleVerticalAlignAttribute.Value.BOTTOM.toString().equals(verticalAlign)) {
      tableCellProperties.setVerticalAlignment(Element.ALIGN_BOTTOM);
    }
  }
  super.visit(ele);
}
