/** 
 * Get the clickable span that is at the exact coordinates
 * @param x x-position of the click
 * @param y y-position of the click
 * @return a clickable span that's located where the click occurred,or:  {@code null} if no clickable span was located there
 */
@Nullable private ClickableSpan getClickableSpanInCoords(int x,int y){
  final int offset=getTextOffsetAt(x,y);
  if (offset < 0) {
    return null;
  }
  final ClickableSpan[] clickableSpans=((Spanned)mText).getSpans(offset,offset,ClickableSpan.class);
  if (clickableSpans != null && clickableSpans.length > 0) {
    return clickableSpans[0];
  }
  return null;
}
