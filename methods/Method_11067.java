/** 
 * Configures text view. Is called for the TEXT_VIEW_ITEM_RESOURCE views.
 * @param view the text view to be configured
 */
protected void configureTextView(TextView view){
  view.setTextColor(textColor);
  view.setGravity(Gravity.CENTER);
  view.setTextSize(textSize);
  view.setLines(1);
  view.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
}
