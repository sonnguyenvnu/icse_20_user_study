private static boolean isInLastRow(int position,int itemCount,SpanSizeLookup spanSizeLookup,int spanCount){
  int totalSpan=0;
  for (int i=itemCount - 1; i >= position; i--) {
    totalSpan+=spanSizeLookup.getSpanSize(i);
    if (totalSpan > spanCount) {
      return false;
    }
  }
  return true;
}
