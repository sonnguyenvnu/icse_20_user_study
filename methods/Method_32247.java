/** 
 * Returns the duration per unit value of this field. For example, if this field represents "minute of hour", then the duration field is minutes.
 * @return the duration of this field, or UnsupportedDurationField if fieldhas no duration
 */
public DurationField getDurationField(){
  return iUnitField;
}
