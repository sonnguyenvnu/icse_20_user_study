/** 
 * Check if this spring should be advanced by the system.  * The rule is if the spring is currently at rest and it was at rest in the previous advance, the system can skip this spring
 * @return should the system process this spring
 */
public boolean systemShouldAdvance(){
  return !isAtRest() || !wasAtRest();
}
