/** 
 * Apply things set this to other
 * @param other facade to apply our data to
 */
void applyTo(DayViewFacade other){
  if (selectionDrawable != null) {
    other.setSelectionDrawable(selectionDrawable);
  }
  if (backgroundDrawable != null) {
    other.setBackgroundDrawable(backgroundDrawable);
  }
  other.spans.addAll(spans);
  other.isDecorated|=this.isDecorated;
  other.daysDisabled=daysDisabled;
}
