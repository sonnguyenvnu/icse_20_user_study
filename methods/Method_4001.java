static void getDecoratedBoundsWithMarginsInt(View view,Rect outBounds){
  final LayoutParams lp=(LayoutParams)view.getLayoutParams();
  final Rect insets=lp.mDecorInsets;
  outBounds.set(view.getLeft() - insets.left - lp.leftMargin,view.getTop() - insets.top - lp.topMargin,view.getRight() + insets.right + lp.rightMargin,view.getBottom() + insets.bottom + lp.bottomMargin);
}
