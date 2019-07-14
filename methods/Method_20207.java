private static boolean shouldReverseLayout(LayoutManager layout,boolean horizontallyScrolling){
  boolean reverseLayout=layout instanceof LinearLayoutManager && ((LinearLayoutManager)layout).getReverseLayout();
  boolean rtl=layout.getLayoutDirection() == ViewCompat.LAYOUT_DIRECTION_RTL;
  if (horizontallyScrolling && rtl) {
    reverseLayout=!reverseLayout;
  }
  return reverseLayout;
}
