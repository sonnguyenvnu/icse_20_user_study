/** 
 * Attempts to return the date that has accessibility focus.
 * @return The date that has accessibility focus, or {@code null} if no datehas focus.
 */
private CalendarDay findAccessibilityFocus(){
  final int childCount=getChildCount();
  for (int i=0; i < childCount; i++) {
    final View child=getChildAt(i);
    if (child instanceof MonthView) {
      final CalendarDay focus=((MonthView)child).getAccessibilityFocus();
      if (focus != null) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) {
          ((MonthView)child).clearAccessibilityFocus();
        }
        return focus;
      }
    }
  }
  return null;
}
