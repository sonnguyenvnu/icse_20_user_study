private void setEnabled(){
  boolean enabled=isInMonth && isInRange && !isDecoratedDisabled;
  super.setEnabled(isInRange && !isDecoratedDisabled);
  boolean showOtherMonths=showOtherMonths(showOtherDates);
  boolean showOutOfRange=showOutOfRange(showOtherDates) || showOtherMonths;
  boolean showDecoratedDisabled=showDecoratedDisabled(showOtherDates);
  boolean shouldBeVisible=enabled;
  if (!isInMonth && showOtherMonths) {
    shouldBeVisible=true;
  }
  if (!isInRange && showOutOfRange) {
    shouldBeVisible|=isInMonth;
  }
  if (isDecoratedDisabled && showDecoratedDisabled) {
    shouldBeVisible|=isInMonth && isInRange;
  }
  if (!isInMonth && shouldBeVisible) {
    setTextColor(getTextColors().getColorForState(new int[]{-android.R.attr.state_enabled},Color.GRAY));
  }
  setVisibility(shouldBeVisible ? View.VISIBLE : View.INVISIBLE);
}
