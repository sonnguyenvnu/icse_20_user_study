/** 
 * {@inheritDoc}
 */
@Override public CharSequence format(final DayOfWeek dayOfWeek){
  return weekDayLabels[dayOfWeek.getValue() - 1];
}
