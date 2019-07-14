public static int parseColor(String colorStr,String defaultColor){
  try {
    return Color.parseColor(colorStr);
  }
 catch (  Exception e) {
    return Color.parseColor(defaultColor);
  }
}
