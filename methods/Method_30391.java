public void setCompact(boolean compact){
  setShowDividers(compact ? SHOW_DIVIDER_NONE : SHOW_DIVIDER_MIDDLE);
  setColumnCollapsed(0,compact);
  for (  RowHolder row : mRowHolders) {
    row.mBarView.setMaxWidthEnabled(!compact);
    ViewUtils.setVisibleOrGone(row.mCountText,!compact);
  }
}
