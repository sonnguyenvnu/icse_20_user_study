public static ImageView.ScaleType scaleType(int value){
  if (0 <= value && value < IMAGE_SCALE_TYPES.length) {
    return IMAGE_SCALE_TYPES[value];
  }
  return IMAGE_SCALE_TYPES[DEFAULT_IMAGE_SCALE_TYPE];
}
