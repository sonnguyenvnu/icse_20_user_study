/** 
 * <p>Tells this generator to add elements to the generated collection a number of times within a specified minimum and/or maximum, inclusive, chosen with uniform distribution.</p> <p>Note that some kinds of collections disallow duplicates, so the number of elements added may not be equal to the collection's {@link Collection#size()}.</p>
 * @param size annotation that gives the size constraints
 */
public void configure(Size size){
  this.sizeRange=size;
  checkRange(INTEGRAL,size.min(),size.max());
}
