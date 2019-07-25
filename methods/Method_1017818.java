/** 
 * Tells this generator to add elements which are distinct from each other.
 * @param distinct Generated elements will be distinct if this param isnot null
 */
public void configure(Distinct distinct){
  this.distinct=distinct != null;
}
