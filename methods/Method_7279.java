/** 
 * <p>Get a string version of this formatter.</p>
 * @return a debugging string
 */
@Override public String toString(){
  return "FastDateParser[" + pattern + "," + locale + "," + timeZone.getID() + "]";
}
