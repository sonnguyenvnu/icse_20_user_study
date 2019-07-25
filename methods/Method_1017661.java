public static int convert(String color,int defaultColor){
  if (color == null) {
    return defaultColor;
  }
  if (color.startsWith(PREFIX)) {
    return parseColor(color,defaultColor);
  }
  if (color.startsWith(REFERENCE_SYSTEM)) {
    return referenceSystem(color);
  }
  if (color.startsWith(REFERENCE_APP)) {
    return referenceApp(color);
  }
  if (color.startsWith(REFERENCE_RGB)) {
    return convertRGBToInteger(color);
  }
  if (keywordColorMaps.containsKey(color)) {
    return keywordColorMaps.get(color);
  }
  return defaultColor;
}
