/** 
 * Creates a range that is rounded to the specified interval.
 * @param interval Interval to round to. Return same range if 0.
 * @return Rounded date range.
 */
public DateRange rounded(long interval){
  if (interval <= 0) {
    return this;
  }
  return new DateRange(start - start % interval,end - (end % interval));
}
