private static int computeRectArea(Rect rect){
  return rect.isEmpty() ? 0 : (rect.width() * rect.height());
}
