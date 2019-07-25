/** 
 * Returns minimum insets combined from the specified ones.
 * @param insets1 first insets
 * @param insets2 second insets
 * @return minimum insets
 */
public static Insets min(final Insets insets1,final Insets insets2){
  if (insets1 != null && insets2 != null) {
    return new Insets(Math.min(insets1.top,insets2.top),Math.min(insets1.left,insets2.left),Math.min(insets1.bottom,insets2.bottom),Math.min(insets1.right,insets2.right));
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
