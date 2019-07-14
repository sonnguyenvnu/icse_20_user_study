public static Integer getColorOrNull(String key){
  Integer color=currentColors.get(key);
  if (color == null) {
    String fallbackKey=fallbackKeys.get(key);
    if (fallbackKey != null) {
      color=currentColors.get(key);
    }
    if (color == null) {
      color=defaultColors.get(key);
    }
  }
  return color;
}
