/** 
 * Tells this generator to produce values which are distinct from each other.
 * @param distinct Generated values will be distinct if this param is notnull.
 */
public void configure(Distinct distinct){
  this.distinct=distinct != null;
}
