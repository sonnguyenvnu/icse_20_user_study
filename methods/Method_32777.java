/** 
 * Gets this instance as a String in the ISO8601 duration format. <p> For example, "P4Y" represents 4 years.
 * @return the value as an ISO8601 string
 */
@ToString public String toString(){
  return "P" + String.valueOf(getValue()) + "Y";
}
