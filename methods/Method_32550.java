/** 
 * Sets the status of rounding to use the specified field and mode. A null field or mode of ROUND_NONE will disable rounding. Once set, the instant is then rounded using the new field and mode. <p> Enabling rounding will cause all subsequent calls to  {@link #setMillis(long)}to be rounded. This can be used to control the precision of the instant, for example by setting a rounding field of minuteOfDay, the seconds and milliseconds will always be zero.
 * @param field  rounding field or null to disable
 * @param mode  rounding mode or ROUND_NONE to disable
 * @throws IllegalArgumentException if mode is unknown, no exception if field is null
 */
public void setRounding(DateTimeField field,int mode){
  if (field != null && (mode < ROUND_NONE || mode > ROUND_HALF_EVEN)) {
    throw new IllegalArgumentException("Illegal rounding mode: " + mode);
  }
  iRoundingField=(mode == ROUND_NONE ? null : field);
  iRoundingMode=(field == null ? ROUND_NONE : mode);
  setMillis(getMillis());
}
