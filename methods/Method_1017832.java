/** 
 * <p>Tells this generator to add key-value pairs to the generated map a number of times within a specified minimum and/or maximum, inclusive, chosen with uniform distribution.</p> <p>Note that maps disallow duplicate keys, so the number of pairs added may not be equal to the map's  {@link Map#size()}.</p>
 * @param size annotation that gives the size constraints
 */
public void configure(Size size){
  this.sizeRange=size;
  checkRange(INTEGRAL,size.min(),size.max());
}
