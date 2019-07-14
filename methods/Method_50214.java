/** 
 * Pass an ordered list of desired aspect ratios that should be available for a user.
 * @param selectedByDefault - index of aspect ratio option that is selected by default (starts with 0).
 * @param aspectRatio       - list of aspect ratio options that are available to user
 */
public RxGalleryFinal cropAspectRatioOptions(int selectedByDefault,AspectRatio... aspectRatio){
  configuration.setSelectedByDefault(selectedByDefault);
  configuration.setAspectRatio(aspectRatio);
  return this;
}
