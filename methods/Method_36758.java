@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper){
  LayoutHelper layoutHelper=mCard.convertLayoutHelper(oldHelper);
  if (layoutHelper != null) {
    layoutHelper.setItemCount(mCells.size());
    if (layoutHelper instanceof RangeGridLayoutHelper) {
      RangeGridLayoutHelper gridHelper=(RangeGridLayoutHelper)layoutHelper;
      gridHelper.setSpanSizeLookup(new GridCard.CellSpanSizeLookup(mCells,gridHelper.getSpanCount()));
    }
  }
  return layoutHelper;
}
