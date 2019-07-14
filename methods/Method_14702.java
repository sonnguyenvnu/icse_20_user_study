/** 
 * Convert a date/time to an ISO_LOCAL_DATE_TIME string
 * @param d the date to be written
 * @return string with ISO_LOCAL_DATE_TIME formatted date & time
 */
static public String dateToString(OffsetDateTime d){
  return d.format(ISO8601);
}
