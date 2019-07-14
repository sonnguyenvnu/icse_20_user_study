/** 
 * Sets the status of rounding to use the specified field and ROUND_FLOOR mode. A null field will disable rounding. Once set, the instant is then rounded using the new field and mode. <p> Enabling rounding will cause all subsequent calls to  {@link #setMillis(long)}to be rounded. This can be used to control the precision of the instant, for example by setting a rounding field of minuteOfDay, the seconds and milliseconds will always be zero.
 * @param field rounding field or null to disable
 */
public void setRounding(DateTimeField field){
  setRounding(field,MutableDateTime.ROUND_FLOOR);
}
