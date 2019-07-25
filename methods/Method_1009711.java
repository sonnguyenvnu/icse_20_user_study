/** 
 * Used to clear the peeking ability. This could be useful for a RecyclerView/ListView, where a recycled item shouldn't use the PeekView, but the original item did.
 * @param view the view we want to stop peeking into
 */
public static void clear(View view){
  view.setOnTouchListener(null);
}
