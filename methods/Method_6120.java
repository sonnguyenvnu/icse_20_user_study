public static boolean isSmallTablet(){
  float minSide=Math.min(displaySize.x,displaySize.y) / density;
  return minSide <= 700;
}
