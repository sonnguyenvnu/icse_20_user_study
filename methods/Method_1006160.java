/** 
 * Format date string to yyyy-mm-dd or yyyy-mm. Keeps the existing String if it does not match one of the following formats: "M/y" (covers 9/15, 9/2015, and 09/2015) "MMMM (dd), yyyy" (covers September 1, 2015 and September, 2015) "yyyy-MM-dd" (covers 2009-1-15) "d.M.uuuu" (covers 15.1.2015)
 */
@Override public String format(String value){
  Optional<Date> parsedDate=Date.parse(value);
  return parsedDate.map(Date::getNormalized).orElse(value);
}
