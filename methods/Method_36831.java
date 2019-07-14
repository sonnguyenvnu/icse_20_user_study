@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper helper){
  RangeGridLayoutHelper gridHelper=new RangeGridLayoutHelper(1,mCells.size());
  gridHelper.setItemCount(mCells.size());
  gridHelper.setSpanCount(mColumn);
  if (style instanceof GridStyle) {
    GridStyle gridStyle=(GridStyle)style;
    int totalColumn=mColumn;
    if (gridStyle.column > 0) {
      totalColumn=gridStyle.column;
      gridHelper.setSpanCount(gridStyle.column);
    }
    gridHelper.setSpanSizeLookup(new CellSpanSizeLookup(mCells,totalColumn));
    gridHelper.setVGap(gridStyle.vGap);
    gridHelper.setHGap(gridStyle.hGap);
    gridHelper.setAutoExpand(gridStyle.autoExpand);
    if (gridStyle.cols != null && gridStyle.cols.length > 0) {
      gridHelper.setWeights(gridStyle.cols);
    }
    if (!Float.isNaN(gridStyle.aspectRatio)) {
      gridHelper.setAspectRatio(gridStyle.aspectRatio);
    }
  }
  gridHelper.getRootRangeStyle().onClearChildMap();
  convertChildLayoutHelper(gridHelper,this);
  return gridHelper;
}
