/** 
 * Creates a period from two partially specified times, calculating by field difference. <p> The two partials must contain the same fields, thus you can specify two <code>LocalDate</code> objects, or two <code>LocalTime</code> objects, but not one of each. Also, the partial may not contain overlapping fields, such as dayOfWeek and dayOfMonth. <p> Calculation by field difference works by extracting the difference one field at a time and not wrapping into other fields. Thus 2005-06-09/2007-04-12 will yield P2Y-2M3D. <p> For example, you have an event that always runs from the 27th of each month to the 2nd of the next month. If you calculate this period using a standard constructor, then you will get between P3D and P6D depending on the month. If you use this method, then you will get P1M-25D. This field-difference based period can be successfully applied to each month of the year to obtain the correct end date for a given start date.
 * @param start  the start of the period, must not be null
 * @param end  the end of the period, must not be null
 * @throws IllegalArgumentException if the partials are null or invalid
 * @since 1.1
 */
public static Period fieldDifference(ReadablePartial start,ReadablePartial end){
  if (start == null || end == null) {
    throw new IllegalArgumentException("ReadablePartial objects must not be null");
  }
  if (start.size() != end.size()) {
    throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
  }
  DurationFieldType[] types=new DurationFieldType[start.size()];
  int[] values=new int[start.size()];
  for (int i=0, isize=start.size(); i < isize; i++) {
    if (start.getFieldType(i) != end.getFieldType(i)) {
      throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
    }
    types[i]=start.getFieldType(i).getDurationType();
    if (i > 0 && types[i - 1] == types[i]) {
      throw new IllegalArgumentException("ReadablePartial objects must not have overlapping fields");
    }
    values[i]=end.getValue(i) - start.getValue(i);
  }
  return new Period(values,PeriodType.forFields(types));
}
