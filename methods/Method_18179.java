/** 
 * Correction for an Android bug on some devices with "special" densities where the system will double-measure with slightly different widths in the same traversal. See https://issuetracker.google.com/issues/73804230 for more details. <p>This hits Litho extra-hard because we create  {@link LayoutState}s in measure in many places, so we try to correct for it here by replacing the widthSpec given to us from above with what we think the correct one is. Even though the double measure will still happen, the incorrect width will not propagate to any vertical RecyclerViews contained within.
 */
public static int correctWidthSpecForAndroidDoubleMeasureBug(Resources resources,PackageManager packageManager,int widthSpec){
  final @SizeSpec.MeasureSpecMode int mode=SizeSpec.getMode(widthSpec);
  if (mode == SizeSpec.UNSPECIFIED) {
    return widthSpec;
  }
  final boolean isChromebook=packageManager.hasSystemFeature(SYSTEM_FEATURE);
  final Configuration configuration=resources.getConfiguration();
  final DisplayMetrics displayMetrics=resources.getDisplayMetrics();
  final float screenDensity=displayMetrics.density;
  final float screenWidthDp=configuration.screenWidthDp;
  final int screenWidthPx=isChromebook ? (int)(screenWidthDp * screenDensity + 0.5f) : displayMetrics.widthPixels;
  final int calculatedScreenWidthPx=(int)(screenDensity * screenWidthDp + 0.5f);
  if (screenWidthPx != calculatedScreenWidthPx && calculatedScreenWidthPx == SizeSpec.getSize(widthSpec)) {
    return SizeSpec.makeSizeSpec(screenWidthPx,mode);
  }
  return widthSpec;
}
