/** 
 * Returns JDN. Note that JDN is not equal to  {@link #integer}. It is calculated by rounding to the nearest integer.
 */
public int getJulianDayNumber(){
  if (fraction >= 0.5) {
    return integer + 1;
  }
  return integer;
}
