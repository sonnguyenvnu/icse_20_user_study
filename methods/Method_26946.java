@NonNull private LayoutParams initParams(@NonNull View view,int left,int top){
  int[] loc=new int[2];
  getLocationOnScreen(loc);
  final LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
  left-=loc[0];
  top-=loc[1];
  layoutParams.leftMargin=left;
  layoutParams.topMargin=top;
  view.setLeft(left);
  view.setTop(top);
  if (view.getMeasuredWidth() != 0) {
    layoutParams.width=view.getMeasuredWidth();
    view.setRight(left + layoutParams.width);
  }
  if (view.getMeasuredHeight() != 0) {
    layoutParams.height=view.getMeasuredHeight();
    view.setBottom(top + layoutParams.height);
  }
  return layoutParams;
}
