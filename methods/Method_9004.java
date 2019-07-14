private void ensurePinnedHeaderLayout(View header,boolean forceLayout){
  if (header.isLayoutRequested() || forceLayout) {
    if (sectionsType == 1) {
      ViewGroup.LayoutParams layoutParams=header.getLayoutParams();
      int heightSpec=MeasureSpec.makeMeasureSpec(layoutParams.height,MeasureSpec.EXACTLY);
      int widthSpec=MeasureSpec.makeMeasureSpec(layoutParams.width,MeasureSpec.EXACTLY);
      try {
        header.measure(widthSpec,heightSpec);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
 else     if (sectionsType == 2) {
      int widthSpec=MeasureSpec.makeMeasureSpec(getMeasuredWidth(),MeasureSpec.EXACTLY);
      int heightSpec=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
      try {
        header.measure(widthSpec,heightSpec);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    header.layout(0,0,header.getMeasuredWidth(),header.getMeasuredHeight());
  }
}
