private int parseColor(String color,int defaultColor){
  if (TextUtils.isEmpty(color)) {
    return defaultColor;
  }
  try {
    return Color.parseColor(color);
  }
 catch (  Exception e) {
    return defaultColor;
  }
}
