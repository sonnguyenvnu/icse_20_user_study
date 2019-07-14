public static void applyScaleType(GenericDraweeHierarchyBuilder builder,final Config config){
switch (config.scaleType) {
case "scale_type_none":
    builder.setActualImageScaleType(null);
  break;
case "scale_type_center":
builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER);
break;
case "scale_type_center_crop":
builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
break;
case "scale_type_center_inside":
builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE);
break;
case "scale_type_fit_center":
builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
break;
case "scale_type_fit_start":
builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START);
break;
case "scale_type_fit_end":
builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END);
break;
case "scale_type_fit_xy":
builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
break;
}
}
