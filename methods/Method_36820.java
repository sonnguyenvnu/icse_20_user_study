@Override public void parseStyle(@Nullable JSONObject data){
  super.parseStyle(data);
  if (data == null) {
    return;
  }
  cell.setIndicatorRadius(Style.parseSize(data.optString(ATTR_INDICATOR_RADIUS),0));
  cell.setIndicatorColor(Style.parseColor(data.optString(ATTR_INDICATOR_COLOR,"#00000000")));
  cell.setIndicatorDefaultColor(Style.parseColor(data.optString(ATTR_INDICATOR_DEFAULT_INDICATOR_COLOR,"#00000000")));
  cell.setAutoScrollInternal(data.optInt(ATTR_AUTOSCROLL));
  cell.setSpecialInterval(data.optJSONObject(ATTR_SPECIAL_INTERVAL));
  cell.setInfinite(data.optBoolean(ATTR_INFINITE));
  cell.setInfiniteMinCount(data.optInt(ATTR_INFINITE_MIN_COUNT));
  cell.setIndicatorFocus(data.optString(ATTR_INDICATOR_FOCUS));
  cell.setIndicatorNor(data.optString(ATTR_INDICATOR_NORMAL));
  cell.setIndicatorGravity(data.optString(ATTR_INDICATOR_GRA));
  cell.setIndicatorPos(data.optString(ATTR_INDICATOR_POS));
  cell.setIndicatorGap(Style.parseSize(data.optString(ATTR_INDICATOR_GAP),0));
  cell.setIndicatorMargin(Style.parseSize(data.optString(ATTR_INDICATOR_MARGIN),0));
  cell.setIndicatorHeight(Style.parseSize(data.optString(ATTR_INDICATOR_HEIGHT),0));
  cell.setPageWidth(data.optDouble(ATTR_PAGE_WIDTH));
  cell.sethGap(Style.parseSize(data.optString(ATTR_HGAP),0));
  cell.itemRatio=data.optDouble(BannerCard.ATTR_ITEM_RATIO,Double.NaN);
  cell.itemMargin[0]=Style.parseSize(data.optString(ATTR_ITEM_MARGIN_LEFT),0);
  cell.itemMargin[1]=Style.parseSize(data.optString(ATTR_ITEM_MARGIN_RIGHT),0);
  if (style != null) {
    cell.setRatio(style.aspectRatio);
    cell.margin=style.margin;
    cell.height=style.height;
  }
}
