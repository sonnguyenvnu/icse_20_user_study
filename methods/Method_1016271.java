/** 
 * Returns maximum insets combined from the specified ones.
 * @param insets1 first insets
 * @param insets2 second insets
 * @return maximum insets
 */
public static Insets max(final Insets insets1,final Insets insets2){
  if (insets1 != null && insets2 != null) {
    return new Insets(Math.max(insets1.top,insets2.top),Math.max(insets1.left,insets2.left),Math.max(insets1.bottom,insets2.bottom),Math.max(insets1.right,insets2.right));
  }
 else   if (insets1 != null) {
    return insets1;
  }
 else   if (insets2 != null) {
    return insets2;
  }
 else {
    return null;
  }
}
