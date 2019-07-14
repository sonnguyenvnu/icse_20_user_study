/** 
 * @param layout A prepared text layout object
 * @return The (zero-indexed) line number at which the text in this layout will be ellipsized, or-1 if no line will be ellipsized.
 */
private static int getEllipsizedLineNumber(Layout layout){
  for (int i=0; i < layout.getLineCount(); ++i) {
    if (layout.getEllipsisCount(i) > 0) {
      return i;
    }
  }
  return -1;
}
