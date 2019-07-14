/** 
 * Create an instance from a resource. The correct resource for the device screen resolution will be used.
 * @param resId resource ID.
 * @return an {@link ImageSource} instance.
 */
@NonNull public static ImageSource resource(int resId){
  return new ImageSource(resId);
}
