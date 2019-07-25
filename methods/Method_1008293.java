/** 
 * Set the radius of the circle. The String value will be parsed by  {@link DistanceUnit}
 * @param radius Value and unit of the circle combined in a string
 * @return this
 */
public CircleBuilder radius(String radius){
  return radius(DistanceUnit.Distance.parseDistance(radius));
}
