private void setMapColors(SparseArray<TextPaint> map){
  for (int a=0; a < map.size(); a++) {
    int flags=map.keyAt(a);
    TextPaint paint=map.valueAt(a);
    if ((flags & TEXT_FLAG_URL) != 0 || (flags & TEXT_FLAG_WEBPAGE_URL) != 0) {
      paint.setColor(getLinkTextColor());
    }
 else {
      paint.setColor(getTextColor());
    }
  }
}
