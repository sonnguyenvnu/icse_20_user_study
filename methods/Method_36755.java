public static int parseColor(String colorString,int defaultColor){
  try {
    Integer integer=colorCache.get(colorString);
    if (integer != null) {
      return integer.intValue();
    }
 else {
      integer=Color.parseColor(colorString);
      colorCache.put(colorString,integer);
      return integer.intValue();
    }
  }
 catch (  Exception e) {
    return defaultColor;
  }
}
