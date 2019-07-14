/** 
 * Animates filenames textview to marquee after a delay. Make sure to set  {@link TextView#setSelected(boolean)} to false in order to stop the marquee later
 */
public static void marqueeAfterDelay(int delayInMillis,ThemedTextView marqueeView){
  new Handler().postDelayed(() -> {
    marqueeView.setSelected(true);
  }
,delayInMillis);
}
