boolean isCompatibleSpec(int widthSpec,int heightSpec){
  final boolean widthIsCompatible=MeasureComparisonUtils.isMeasureSpecCompatible(mWidthSpec,widthSpec,mWidth);
  final boolean heightIsCompatible=MeasureComparisonUtils.isMeasureSpecCompatible(mHeightSpec,heightSpec,mHeight);
  return widthIsCompatible && heightIsCompatible;
}
