private void layout(View child,ViewPosition pos){
  LayoutParams lp=(LayoutParams)child.getLayoutParams();
  if (mOrientation == HORIZONTAL)   child.layout(pos.left,pos.top + lp.topMargin,pos.left + child.getMeasuredWidth(),pos.top + child.getMeasuredHeight() + lp.topMargin);
 else   child.layout(pos.left + lp.leftMargin,pos.top,pos.left + child.getMeasuredWidth() + lp.leftMargin,pos.top + child.getMeasuredHeight());
}
