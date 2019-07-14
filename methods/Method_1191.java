/** 
 * Sets the rounded corners radii.
 * @param topLeft top-left corner radius in pixels
 * @param topRight top-right corner radius in pixels
 * @param bottomRight bottom-right corner radius in pixels
 * @param bottomLeft bottom-left corner radius in pixels
 * @return modified instance
 */
public RoundingParams setCornersRadii(float topLeft,float topRight,float bottomRight,float bottomLeft){
  float[] radii=getOrCreateRoundedCornersRadii();
  radii[0]=radii[1]=topLeft;
  radii[2]=radii[3]=topRight;
  radii[4]=radii[5]=bottomRight;
  radii[6]=radii[7]=bottomLeft;
  return this;
}
