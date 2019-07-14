/** 
 * Try to speak the specified text, for accessibility. Only available on JB or later.
 * @param text Text to announce.
 */
@SuppressLint("NewApi") public static void tryAccessibilityAnnounce(View view,CharSequence text){
  if (isJellybeanOrLater() && view != null && text != null) {
    view.announceForAccessibility(text);
  }
}
