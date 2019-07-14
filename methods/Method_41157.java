/** 
 * Set the TimeZone for the Date that will be built by this builder (if "null", system default will be used)
 */
public DateBuilder inTimeZone(TimeZone timezone){
  this.tz=timezone;
  return this;
}
