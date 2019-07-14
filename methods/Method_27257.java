public static boolean isEllipsed(@NonNull TextView textView){
  Layout layout=textView.getLayout();
  if (layout != null) {
    int lines=layout.getLineCount();
    if (lines > 0) {
      return IntStream.range(0,lines).anyMatch(line -> layout.getEllipsisCount(line) > 0);
    }
  }
  return false;
}
