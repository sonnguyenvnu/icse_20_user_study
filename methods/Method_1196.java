/** 
 * Factory method that creates new RoundingParams with the specified corners radii. 
 */
public static RoundingParams fromCornersRadii(float[] radii){
  return (new RoundingParams()).setCornersRadii(radii);
}
