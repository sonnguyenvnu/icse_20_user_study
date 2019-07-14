private static boolean isInFirstRow(int position,SpanSizeLookup spanSizeLookup,int spanCount){
  int totalSpan=0;
  for (int i=0; i <= position; i++) {
    totalSpan+=spanSizeLookup.getSpanSize(i);
    if (totalSpan > spanCount) {
      return false;
    }
  }
  return true;
}
