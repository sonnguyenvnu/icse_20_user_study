@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper){
  OnePlusNLayoutHelper layoutHelper;
  if (oldHelper instanceof OnePlusNLayoutHelper) {
    layoutHelper=(OnePlusNLayoutHelper)oldHelper;
  }
 else {
    layoutHelper=new OnePlusNLayoutHelper();
  }
  layoutHelper.setItemCount(mCells.size());
  if (mCells.size() == 1) {
    BaseCell isHeader=mCells.get(0);
    layoutHelper.setHasHeader(Style.DISPLAY_BLOCK.equalsIgnoreCase(isHeader.optStringParam(Style.KEY_DISPLAY)));
    layoutHelper.setHasFooter(false);
  }
 else   if (mCells.size() >= 2) {
    BaseCell isHeader=mCells.get(0);
    layoutHelper.setHasHeader(Style.DISPLAY_BLOCK.equalsIgnoreCase(isHeader.optStringParam(Style.KEY_DISPLAY)));
    BaseCell isFooter=mCells.get(mCells.size() - 1);
    layoutHelper.setHasFooter(Style.DISPLAY_BLOCK.equalsIgnoreCase(isFooter.optStringParam(Style.KEY_DISPLAY)));
  }
  if (style instanceof ColumnStyle) {
    ColumnStyle columnStyle=(ColumnStyle)style;
    if (columnStyle.cols != null && columnStyle.cols.length > 0)     layoutHelper.setColWeights(columnStyle.cols);
 else     layoutHelper.setColWeights(EMPTY_WEIGHTS);
    if (!Float.isNaN(style.aspectRatio)) {
      layoutHelper.setAspectRatio(style.aspectRatio);
    }
    if (columnStyle.rows != null && columnStyle.rows.length > 0) {
      layoutHelper.setRowWeight(columnStyle.rows[0]);
    }
    layoutHelper.setBgColor(columnStyle.bgColor);
    layoutHelper.setMargin(style.margin[Style.MARGIN_LEFT_INDEX],style.margin[Style.MARGIN_TOP_INDEX],style.margin[Style.MARGIN_RIGHT_INDEX],style.margin[Style.MARGIN_BOTTOM_INDEX]);
    layoutHelper.setPadding(style.padding[Style.MARGIN_LEFT_INDEX],style.padding[Style.MARGIN_TOP_INDEX],style.padding[Style.MARGIN_RIGHT_INDEX],style.padding[Style.MARGIN_BOTTOM_INDEX]);
  }
  return layoutHelper;
}
