private Integer getStartOffset(Integer[] colors){
  Integer start=0;
  for (int i=0; i < colors.length; i++) {
    if (colors[i] == null) {
      return start;
    }
    start=(i + 1) / 2;
  }
  return start;
}
