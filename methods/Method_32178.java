/** 
 * Gets the amount by which this field is leap.
 * @return the amount by which the field is leap
 * @see DateTimeField#getLeapAmount
 */
public int getLeapAmount(){
  return getField().getLeapAmount(getMillis());
}
