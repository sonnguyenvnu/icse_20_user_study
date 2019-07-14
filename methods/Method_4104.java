/** 
 * Finds the last View that can be used as an anchor View.
 * @return Position of the View or 0 if it cannot find any such View.
 */
private int findLastReferenceChildPosition(int itemCount){
  for (int i=getChildCount() - 1; i >= 0; i--) {
    final View view=getChildAt(i);
    final int position=getPosition(view);
    if (position >= 0 && position < itemCount) {
      return position;
    }
  }
  return 0;
}
