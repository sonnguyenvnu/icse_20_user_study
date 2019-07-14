public static int getShadowHeight(){
  if (density >= 4.0f) {
    return 3;
  }
 else   if (density >= 2.0f) {
    return 2;
  }
 else {
    return 1;
  }
}
