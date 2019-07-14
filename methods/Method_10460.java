private AspectRatio chooseAspectRatio(){
  AspectRatio r=null;
  for (  AspectRatio ratio : mPreviewSizes.ratios()) {
    r=ratio;
    if (ratio.equals(Constants.DEFAULT_ASPECT_RATIO)) {
      return ratio;
    }
  }
  return r;
}
