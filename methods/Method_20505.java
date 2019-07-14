/** 
 * Adjust a requested scale to be within the allowed limits.
 */
private float limitedScale(float targetScale){
  targetScale=Math.max(minScale(),targetScale);
  targetScale=Math.min(maxScale,targetScale);
  return targetScale;
}
