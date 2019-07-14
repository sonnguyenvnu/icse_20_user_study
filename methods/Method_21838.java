/** 
 * Used for enabling or disabling views, while also changing the alpha.
 * @param view The view to enable or disable.
 * @param enable Whether to enable or disable the view.
 */
private static void enableView(final View view,final boolean enable){
  view.setEnabled(enable);
  view.setAlpha(enable ? 1f : 0.1f);
}
