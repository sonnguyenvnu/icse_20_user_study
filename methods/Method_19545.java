private static int getIndicatorSize(int numPages,int firstPageInWindow,int position,int selectedPage){
  if (firstPageInWindow == 0 && selectedPage <= MAX_LARGE_DOT_COUNT - 1) {
    if (position == MAX_DOT_COUNT - 1) {
      return RADIUS_SMALL;
    }
 else     if (position == MAX_DOT_COUNT - 2) {
      return RADIUS_MEDIUM;
    }
 else {
      return RADIUS_LARGE;
    }
  }
  if (firstPageInWindow == numPages - MAX_DOT_COUNT && selectedPage >= numPages - MAX_LARGE_DOT_COUNT) {
    if (position == 0) {
      return RADIUS_SMALL;
    }
 else     if (position == 1) {
      return RADIUS_MEDIUM;
    }
 else {
      return RADIUS_LARGE;
    }
  }
  if (position == 0 || position == MAX_DOT_COUNT - 1) {
    return RADIUS_MEDIUM;
  }
 else {
    return RADIUS_LARGE;
  }
}
