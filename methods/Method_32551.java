/** 
 * Set the milliseconds of the datetime. <p> All changes to the millisecond field occurs via this method.
 * @param instant  the milliseconds since 1970-01-01T00:00:00Z to set thedatetime to
 */
public void setMillis(long instant){
switch (iRoundingMode) {
case ROUND_NONE:
    break;
case ROUND_FLOOR:
  instant=iRoundingField.roundFloor(instant);
break;
case ROUND_CEILING:
instant=iRoundingField.roundCeiling(instant);
break;
case ROUND_HALF_FLOOR:
instant=iRoundingField.roundHalfFloor(instant);
break;
case ROUND_HALF_CEILING:
instant=iRoundingField.roundHalfCeiling(instant);
break;
case ROUND_HALF_EVEN:
instant=iRoundingField.roundHalfEven(instant);
break;
}
super.setMillis(instant);
}
