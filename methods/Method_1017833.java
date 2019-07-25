/** 
 * Tells this generator to add entries whose keys are distinct from each other.
 * @param distinct Keys of generated entries will be distinct if thisparam is not null
 */
public void configure(Distinct distinct){
  this.distinct=distinct != null;
}
