/** 
 * Returns a copy of this ZonedDateTime changing the zone offset to the later of the two valid offsets at a local time-line overlap. <p> This method only has any effect when the local time-line overlaps, such as at an autumn daylight savings cutover. In this scenario, there are two valid offsets for the local date-time. Calling this method will return a date-time with the later of the two selected. <p> If this method is called when it is not an overlap, this is returned. <p> This instance is immutable and unaffected by this method call.
 * @return a copy of this datetime with the latest valid offset for the local datetime
 */
public DateTime withLaterOffsetAtOverlap(){
  long newMillis=getZone().adjustOffset(getMillis(),true);
  return withMillis(newMillis);
}
