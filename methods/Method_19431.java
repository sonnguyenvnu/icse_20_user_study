private static boolean containsLongClickableSpan(@Nullable ClickableSpan[] clickableSpans){
  if (clickableSpans == null) {
    return false;
  }
  for (  ClickableSpan span : clickableSpans) {
    if (span instanceof LongClickableSpan) {
      return true;
    }
  }
  return false;
}
