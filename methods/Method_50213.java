/** 
 * This method sets multiplier that is used to calculate max image scale from min image scale.
 * @param maxScaleMultiplier - (minScale * maxScaleMultiplier) = maxScale
 */
public RxGalleryFinal cropMaxScaleMultiplier(@FloatRange(from=1.0,fromInclusive=false) float maxScaleMultiplier){
  configuration.setMaxScaleMultiplier(maxScaleMultiplier);
  return this;
}
