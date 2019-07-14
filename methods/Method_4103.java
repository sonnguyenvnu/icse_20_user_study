/** 
 * Finds the first View that can be used as an anchor View.
 * @return Position of the View or 0 if it cannot find any such View.
 */
private int findFirstReferenceChildPosition(int itemCount){
  final int limit=getChildCount();
  for (int i=0; i < limit; i++) {
    final View view=getChildAt(i);
    final int position=getPosition(view);
    if (position >= 0 && position < itemCount) {
      return position;
    }
  }
  return 0;
}
