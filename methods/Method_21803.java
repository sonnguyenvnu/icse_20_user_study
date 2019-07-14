/** 
 * {@inheritDoc}
 */
@Override public CharSequence format(final DayOfWeek dayOfWeek){
  return dayOfWeek.getDisplayName(TextStyle.SHORT,Locale.getDefault());
}
