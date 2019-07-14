/** 
 * Factory method that creates new RoundingParams with the specified corners radii. 
 */
public static RoundingParams fromCornersRadii(float topLeft,float topRight,float bottomRight,float bottomLeft){
  return (new RoundingParams()).setCornersRadii(topLeft,topRight,bottomRight,bottomLeft);
}
