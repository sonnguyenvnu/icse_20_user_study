private void measureChildrenWithMargins(int widthSpec,int heightSpec,boolean firstPass){
  int N=getChildCount();
  for (int i=0; i < N; i++) {
    Child c=getChildAt(i);
    LayoutParams lp=c.getLayoutParams();
    if (firstPass) {
      int width=MeasureSpec.getSize(widthSpec);
      int maxCellWidth;
      if (colCount == 2) {
        maxCellWidth=(int)(width / 2.0f) - itemPaddingLeft * 4;
      }
 else {
        maxCellWidth=(int)(width / 1.5f);
      }
      c.setTextLayout(delegate.createTextLayout(c.cell,maxCellWidth));
      if (c.textLayout != null) {
        lp.width=c.textWidth + itemPaddingLeft * 2;
        lp.height=c.textHeight + itemPaddingTop * 2;
      }
 else {
        lp.width=0;
        lp.height=0;
      }
      measureChildWithMargins2(c,widthSpec,heightSpec,lp.width,lp.height,true);
    }
 else {
      boolean horizontal=(mOrientation == HORIZONTAL);
      Spec spec=horizontal ? lp.columnSpec : lp.rowSpec;
      if (spec.getAbsoluteAlignment(horizontal) == FILL) {
        Interval span=spec.span;
        Axis axis=horizontal ? mHorizontalAxis : mVerticalAxis;
        int[] locations=axis.getLocations();
        int cellSize=locations[span.max] - locations[span.min];
        int viewSize=cellSize - getTotalMargin(c,horizontal);
        if (horizontal) {
          measureChildWithMargins2(c,widthSpec,heightSpec,viewSize,lp.height,false);
        }
 else {
          measureChildWithMargins2(c,widthSpec,heightSpec,lp.width,viewSize,false);
        }
      }
    }
  }
}
