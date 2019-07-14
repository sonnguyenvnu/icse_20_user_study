/** 
 * A debugging string for the chronology.
 * @return the debugging string
 */
public String toString(){
  return "ZonedChronology[" + getBase() + ", " + getZone().getID() + ']';
}
