/** 
 * Truncates text which is too long and appends the given custom ellipsis CharSequence to the end of the visible text.
 * @param text Text to truncate
 * @param customEllipsisText Text to append to the end to indicate truncation happened
 * @param newLayout A Layout object populated with measurement information for this text
 * @param ellipsizedLineNumber The line number within the text at which truncation occurs (i.e.the last visible line).
 * @return The provided text truncated in such a way that the 'customEllipsisText' can appear atthe end.
 */
private static CharSequence truncateText(CharSequence text,CharSequence customEllipsisText,Layout newLayout,int ellipsizedLineNumber,float layoutWidth){
  Rect bounds=new Rect();
  newLayout.getPaint().getTextBounds(customEllipsisText.toString(),0,customEllipsisText.length(),bounds);
  final float ellipsisTarget=layoutWidth - bounds.width();
  final int ellipsisOffset=newLayout.getOffsetForHorizontal(ellipsizedLineNumber,ellipsisTarget);
  if (ellipsisOffset > 0) {
    return TextUtils.concat(text.subSequence(0,ellipsisOffset - 1),customEllipsisText);
  }
 else {
    return text;
  }
}
