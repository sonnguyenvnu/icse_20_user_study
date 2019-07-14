/** 
 * Sets the milliseconds of the datetime. <p> All changes to the millisecond field occurs via this method. Override and block this method to make a subclass immutable.
 * @param instant  the milliseconds since 1970-01-01T00:00:00Z to set the datetime to
 */
protected void setMillis(long instant){
  iMillis=checkInstant(instant,iChronology);
}
