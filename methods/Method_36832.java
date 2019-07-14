private void convertChildLayoutHelper(@Nullable RangeGridLayoutHelper gridHelper,GridCard parentCard){
  for (  Map.Entry<Range<Integer>,Card> entry : parentCard.getChildren().entrySet()) {
    Range<Integer> range=entry.getKey();
    Card child=entry.getValue();
    Style style=child.style;
    if (style instanceof GridStyle && child instanceof GridCard) {
      final GridStyle gridStyle=(GridStyle)style;
      final GridCard gridCard=(GridCard)child;
      if (!gridCard.getChildren().isEmpty()) {
        convertChildLayoutHelper(gridHelper,gridCard);
      }
      GridRangeStyle rangeStyle=new GridRangeStyle();
      int totalColumn=gridCard.mColumn;
      if (gridStyle.column > 0) {
        totalColumn=gridStyle.column;
        rangeStyle.setSpanCount(gridStyle.column);
      }
 else {
        rangeStyle.setSpanCount(totalColumn);
      }
      rangeStyle.setSpanSizeLookup(new CellSpanSizeLookup(gridCard.getCells(),totalColumn));
      rangeStyle.setVGap(gridStyle.vGap);
      rangeStyle.setHGap(gridStyle.hGap);
      rangeStyle.setAutoExpand(gridStyle.autoExpand);
      if (gridStyle.cols != null && gridStyle.cols.length > 0) {
        rangeStyle.setWeights(gridStyle.cols);
      }
      if (!Float.isNaN(gridStyle.aspectRatio)) {
        rangeStyle.setAspectRatio(gridStyle.aspectRatio);
      }
      rangeStyle.setBgColor(style.bgColor);
      rangeStyle.setMargin(style.margin[Style.MARGIN_LEFT_INDEX],style.margin[Style.MARGIN_TOP_INDEX],style.margin[Style.MARGIN_RIGHT_INDEX],style.margin[Style.MARGIN_BOTTOM_INDEX]);
      rangeStyle.setPadding(style.padding[Style.MARGIN_LEFT_INDEX],style.padding[Style.MARGIN_TOP_INDEX],style.padding[Style.MARGIN_RIGHT_INDEX],style.padding[Style.MARGIN_BOTTOM_INDEX]);
      if (!TextUtils.isEmpty(style.bgImgUrl)) {
        if (serviceManager != null && serviceManager.getService(CardSupport.class) != null) {
          final CardSupport support=serviceManager.getService(CardSupport.class);
          rangeStyle.setLayoutViewBindListener(new BindListener(style){
            @Override public void onBind(            View layoutView,            BaseLayoutHelper baseLayoutHelper){
              support.onBindBackgroundView(layoutView,gridCard);
            }
          }
);
          rangeStyle.setLayoutViewUnBindListener(new UnbindListener(style){
            @Override public void onUnbind(            View layoutView,            BaseLayoutHelper baseLayoutHelper){
              support.onUnbindBackgroundView(layoutView,gridCard);
            }
          }
);
        }
 else {
          rangeStyle.setLayoutViewBindListener(new BindListener(style));
          rangeStyle.setLayoutViewUnBindListener(new UnbindListener(style));
        }
      }
 else {
        rangeStyle.setLayoutViewBindListener(null);
        rangeStyle.setLayoutViewUnBindListener(null);
      }
      gridHelper.addRangeStyle(range.getLower().intValue(),range.getUpper().intValue(),rangeStyle);
    }
  }
}
