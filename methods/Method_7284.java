/** 
 * Adjust dates to be within appropriate century
 * @param twoDigitYear The year to adjust
 * @return A value between centuryStart(inclusive) to centuryStart+100(exclusive)
 */
private int adjustYear(final int twoDigitYear){
  int trial=century + twoDigitYear;
  return twoDigitYear >= startYear ? trial : trial + 100;
}
