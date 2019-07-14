@Override public void parseStyle(@Nullable JSONObject data){
  super.parseStyle(data);
  if (data != null) {
    cell.pageWidth=Style.parseSize(optStringParam(LinearScrollCell.KEY_PAGE_WIDTH),0);
    cell.pageHeight=Style.parseSize(optStringParam(LinearScrollCell.KEY_PAGE_HEIGHT),0);
    cell.defaultIndicatorColor=parseColor(optStringParam(LinearScrollCell.KEY_DEFAULT_INDICATOR_COLOR),LinearScrollCell.DEFAULT_DEFAULT_INDICATOR_COLOR);
    cell.indicatorColor=parseColor(optStringParam(LinearScrollCell.KEY_INDICATOR_COLOR),LinearScrollCell.DEFAULT_INDICATOR_COLOR);
    if (data.has(LinearScrollCell.KEY_HAS_INDICATOR)) {
      cell.hasIndicator=data.optBoolean(LinearScrollCell.KEY_HAS_INDICATOR);
    }
    cell.indicatorHeight=Style.parseSize(optStringParam(LinearScrollCell.KEY_INDICATOR_HEIGHT),LinearScrollCell.DEFAULT_INDICATOR_HEIGHT);
    cell.indicatorWidth=Style.parseSize(optStringParam(LinearScrollCell.KEY_INDICATOR_WIDTH),LinearScrollCell.DEFAULT_INDICATOR_WIDTH);
    cell.defaultIndicatorWidth=Style.parseSize(optStringParam(LinearScrollCell.KEY_DEFAULT_INDICATOR_WIDTH),LinearScrollCell.DEFAULT_DEFAULT_INDICATOR_WIDTH);
    cell.indicatorMargin=Style.parseSize(optStringParam(LinearScrollCell.KEY_INDICATOR_MARGIN),LinearScrollCell.DEFAULT_INDICATOR_MARGIN);
    if (data.has(LinearScrollCell.KEY_FOOTER_TYPE)) {
      cell.footerType=data.optString(LinearScrollCell.KEY_FOOTER_TYPE);
    }
    cell.bgColor=parseColor(data.optString(Style.KEY_BG_COLOR),Color.TRANSPARENT);
    cell.retainScrollState=data.optBoolean(LinearScrollCell.KEY_RETAIN_SCROLL_STATE,true);
    cell.scrollMarginLeft=Style.parseSize(data.optString(LinearScrollCell.KEY_SCROLL_MARGIN_LEFT),0);
    cell.scrollMarginRight=Style.parseSize(data.optString(LinearScrollCell.KEY_SCROLL_MARGIN_RIGHT),0);
    cell.hGap=Style.parseSize(optStringParam(LinearScrollCell.KEY_HGAP),0);
    cell.vGap=Style.parseSize(optStringParam(LinearScrollCell.KEY_VGAP),0);
    cell.maxRows=data.optInt(LinearScrollCell.KEY_MAX_ROWS,LinearScrollCell.DEFAULT_MAX_ROWS);
    cell.maxCols=data.optInt(LinearScrollCell.KEY_MAX_COLS,0);
  }
}
