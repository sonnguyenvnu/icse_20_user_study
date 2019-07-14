/** 
 * Derives the device's orientation (portrait/landscape) from the `context`.
 */
protected @NonNull String deviceOrientation(){
  if (this.context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
    return "landscape";
  }
  return "portrait";
}
