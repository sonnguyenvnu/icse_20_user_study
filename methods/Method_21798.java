/** 
 * @param facade apply the facade to us
 */
void applyFacade(DayViewFacade facade){
  this.isDecoratedDisabled=facade.areDaysDisabled();
  setEnabled();
  setCustomBackground(facade.getBackgroundDrawable());
  setSelectionDrawable(facade.getSelectionDrawable());
  List<DayViewFacade.Span> spans=facade.getSpans();
  if (!spans.isEmpty()) {
    String label=getLabel();
    SpannableString formattedLabel=new SpannableString(getLabel());
    for (    DayViewFacade.Span span : spans) {
      formattedLabel.setSpan(span.span,0,label.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    setText(formattedLabel);
  }
 else {
    setText(getLabel());
  }
}
