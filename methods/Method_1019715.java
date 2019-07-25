@Override public void visit(StyleTableRowPropertiesElement ele){
  StyleTableRowProperties tableRowProperties=currentStyle.getTableRowProperties();
  if (tableRowProperties == null) {
    tableRowProperties=new StyleTableRowProperties();
    currentStyle.setTableRowProperties(tableRowProperties);
  }
  String minHeight=ele.getStyleMinRowHeightAttribute();
  if (StringUtils.isNotEmpty(minHeight)) {
    tableRowProperties.setMinRowHeight(ODFUtils.getDimensionAsPoint(minHeight));
  }
  String height=ele.getStyleRowHeightAttribute();
  if (StringUtils.isNotEmpty(height)) {
    tableRowProperties.setRowHeight(ODFUtils.getDimensionAsPoint(height));
  }
  String keepTogether=ele.getFoKeepTogetherAttribute();
  if (StringUtils.isNotEmpty(keepTogether)) {
    if (FoKeepTogetherAttribute.Value.ALWAYS.toString().equals(keepTogether)) {
      tableRowProperties.setKeepTogether(Boolean.TRUE);
    }
 else {
      tableRowProperties.setKeepTogether(Boolean.FALSE);
    }
  }
}
