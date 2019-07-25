/** 
 * Tells this generator to produce values with a length within a specified minimum and/or maximum, inclusive, chosen with uniform distribution.
 * @param size annotation that gives the length constraints
 */
public void configure(Size size){
  this.lengthRange=size;
  checkRange(INTEGRAL,size.min(),size.max());
}
