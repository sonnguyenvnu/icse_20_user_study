/** 
 * @param disappearedScale Value of scale on start of appearing or in finish of disappearing.Default value is 0. Can be useful for mixing some Visibility transitions, for example Scale and Fade
 * @return This Scale object.
 */
@NonNull public Scale setDisappearedScale(float disappearedScale){
  if (disappearedScale < 0f) {
    throw new IllegalArgumentException("disappearedScale cannot be negative!");
  }
  mDisappearedScale=disappearedScale;
  return this;
}
