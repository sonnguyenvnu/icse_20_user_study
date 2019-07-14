@Override public void cellInited(BaseCell cell){
  if (cell instanceof LinearScrollCell) {
    this.lSCell=(LinearScrollCell)cell;
    if (lSCell.maxRows > 1) {
      layoutManager.setSpanCount(lSCell.maxRows);
    }
 else {
      layoutManager.setSpanCount(1);
    }
    totalDistanceOfIndicator=(float)(this.lSCell.defaultIndicatorWidth - this.lSCell.indicatorWidth);
  }
}
