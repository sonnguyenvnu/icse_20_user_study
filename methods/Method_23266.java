/** 
 * <p>Convert an integer to a boolean. Because of how Java handles upgrading numbers, this will also cover byte and char (as they will upgrade to an int without any sort of explicit cast).</p> <p>The preprocessor will convert boolean(what) to parseBoolean(what).</p>
 * @return false if 0, true if any other number
 */
static final public boolean parseBoolean(int what){
  return (what != 0);
}
