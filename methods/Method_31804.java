/** 
 * Get the date time as a <code>java.util.Date</code>. <p> The <code>Date</code> object created has exactly the same millisecond instant as this object.
 * @return a Date initialised with this datetime
 */
public Date toDate(){
  return new Date(getMillis());
}
