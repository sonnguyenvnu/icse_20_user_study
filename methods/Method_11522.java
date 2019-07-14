/** 
 * Update the parallax based on the current slide offset.
 */
@SuppressLint("NewApi") private void applyParallaxForCurrentSlideOffset(){
  if (mParallaxOffset > 0) {
    int mainViewOffset=getCurrentParallaxOffset();
    ViewCompat.setTranslationY(mMainView,mainViewOffset);
  }
}
