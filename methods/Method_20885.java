/** 
 * Sets the visibility of a view to  {@link View#VISIBLE} or {@link View#INVISIBLE}. Setting the view to INVISIBLE makes it hidden, but it still takes up space.
 */
public static void setInvisible(final @NonNull View view,final boolean hidden){
  if (hidden) {
    view.setVisibility(View.INVISIBLE);
  }
 else {
    view.setVisibility(View.VISIBLE);
  }
}
