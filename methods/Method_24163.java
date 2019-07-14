/** 
 * Set the light falloff rates for the last light that was created. Default is lightFalloff(1, 0, 0).
 */
@Override public void lightFalloff(float constant,float linear,float quadratic){
  currentLightFalloffConstant=constant;
  currentLightFalloffLinear=linear;
  currentLightFalloffQuadratic=quadratic;
}
