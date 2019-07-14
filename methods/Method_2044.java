/** 
 * Creates the Hierarchy using the information into the Config
 * @param context The Context
 * @param config  The Config object
 * @return The Hierarchy to use
 */
public static GenericDraweeHierarchy createDraweeHierarchy(final Context context,final Config config){
  FrescoSystrace.beginSection("DraweeUtil#createDraweeHierarchy");
  GenericDraweeHierarchyBuilder builder=new GenericDraweeHierarchyBuilder(context.getResources()).setFadeDuration(config.fadeDurationMs).setPlaceholderImage(Const.PLACEHOLDER).setFailureImage(Const.FAILURE).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
  applyScaleType(builder,config);
  if (config.useRoundedCorners || config.drawBorder) {
    final Resources res=context.getResources();
    final RoundingParams roundingParams=new RoundingParams();
    if (config.useRoundedCorners) {
      roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
      roundingParams.setCornersRadius(res.getDimensionPixelSize(R.dimen.drawee_corner_radius));
      roundingParams.setRoundAsCircle(config.useRoundedAsCircle);
    }
    if (config.drawBorder) {
      roundingParams.setBorderColor(res.getColor(R.color.colorPrimary));
      roundingParams.setBorderWidth(res.getDimensionPixelSize(R.dimen.drawee_border_width));
    }
    builder.setRoundingParams(roundingParams);
  }
  GenericDraweeHierarchy result=builder.build();
  FrescoSystrace.endSection();
  return result;
}
