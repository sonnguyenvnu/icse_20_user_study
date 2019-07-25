public void merge(Style style,boolean fullPropagation){
  if (fullPropagation) {
    if (style.getPageLayoutProperties() != null) {
      pageLayoutProperties=style.getPageLayoutProperties();
    }
    if (style.getHeaderProperties() != null) {
      headerProperties=style.getHeaderProperties();
    }
    if (style.getFooterProperties() != null) {
      footerProperties=style.getFooterProperties();
    }
    if (style.getTabStopPropertiesList() != null) {
      tabStopPropertiesList=new ArrayList<StyleTabStopProperties>(style.getTabStopPropertiesList());
      tabStopPropertiesList=Collections.unmodifiableList(tabStopPropertiesList);
    }
    if (style.getListPropertiesMap() != null) {
      listPropertiesMap=new HashMap<Integer,StyleListProperties>(style.getListPropertiesMap());
      listPropertiesMap=Collections.unmodifiableMap(listPropertiesMap);
    }
    if (style.getOutlinePropertiesMap() != null) {
      outlinePropertiesMap=new HashMap<Integer,StyleListProperties>(style.getOutlinePropertiesMap());
      outlinePropertiesMap=Collections.unmodifiableMap(outlinePropertiesMap);
    }
    if (style.getSectionProperties() != null) {
      sectionProperties=style.getSectionProperties();
    }
    if (style.getColumnPropertiesList() != null) {
      columnPropertiesList=new ArrayList<StyleColumnProperties>(style.getColumnPropertiesList());
      columnPropertiesList=Collections.unmodifiableList(columnPropertiesList);
    }
  }
  if (paragraphProperties == null) {
    if (style.getParagraphProperties() != null) {
      paragraphProperties=new StyleParagraphProperties(style.getParagraphProperties());
    }
  }
 else {
    if (style.getParagraphProperties() != null) {
      paragraphProperties.merge(style.getParagraphProperties());
    }
  }
  if (textProperties == null) {
    if (style.getTextProperties() != null) {
      textProperties=new StyleTextProperties(fontProvider,style.getTextProperties());
    }
  }
 else {
    if (style.getTextProperties() != null) {
      textProperties.merge(style.getTextProperties());
    }
  }
  if (tableProperties == null) {
    if (style.getTableProperties() != null) {
      tableProperties=new StyleTableProperties(style.getTableProperties());
    }
  }
 else {
    if (style.getTableProperties() != null) {
      tableProperties.merge(style.getTableProperties());
    }
  }
  if (tableRowProperties == null) {
    if (style.getTableRowProperties() != null) {
      tableRowProperties=new StyleTableRowProperties(style.getTableRowProperties());
    }
  }
 else {
    if (style.getTableRowProperties() != null) {
      tableRowProperties.merge(style.getTableRowProperties());
    }
  }
  if (tableCellProperties == null) {
    if (style.getTableCellProperties() != null) {
      tableCellProperties=new StyleTableCellProperties(style.getTableCellProperties());
    }
  }
 else {
    if (style.getTableCellProperties() != null) {
      tableCellProperties.merge(style.getTableCellProperties());
    }
  }
  if (graphicProperties == null) {
    if (style.getGraphicProperties() != null) {
      graphicProperties=new StyleGraphicProperties(style.getGraphicProperties());
    }
  }
 else {
    if (style.getGraphicProperties() != null) {
      graphicProperties.merge(style.getGraphicProperties());
    }
  }
}
